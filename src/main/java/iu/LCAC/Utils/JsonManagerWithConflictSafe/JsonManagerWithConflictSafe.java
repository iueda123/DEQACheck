package iu.LCAC.Utils.JsonManagerWithConflictSafe;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * JsonManagerWithConflictSafe
 * ----------------------
 * <p>
 * 目的: 保存時に、読み取り時（ロード時）とファイルの更新タイムスタンプが異なる場合、
 * ユーザに「キャンセル / 上書き / リロード / 別名保存」を選択させる。
 * この版では **ファイルロック機構は使用しない**（要求により削除）。
 * <p>
 * 動作:
 * - オープン時に内容を読み込み、ロード時の mtime とハッシュを記録。
 * - 保存時にディスク上の mtime を再確認し、異なれば競合ダイアログを表示。
 * - キャンセル: 何もしない
 * - 上書き: ディスクの変更を無視して現在のテキストを保存
 * - リロード: ディスク上の内容でエディタを置き換え（ローカル編集は破棄）
 * - 別名保存: 現在のテキストを別ファイルとして保存し、以降はそのファイルを編集中として扱う
 * - showLoadedContent() でロードされているファイルの内容を確認できる。
 * <p>
 * <p>
 * 2025.10.14 ConflictSafeDitorDemo.javaとJsonManager.javaを融合させて作成。
 * https://chatgpt.com/c/68e796fa-5aa8-8322-b3dc-2a26e5861832 を参考に作成。
 */
public class JsonManagerWithConflictSafe extends JsonManager {


    private static final long UNINITIALIZED_MTIME = -1L;
    private static final ZoneId JST = ZoneId.of("Asia/Tokyo");
    private static final DateTimeFormatter JST_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

    //GUI
    private final JsonManagerCallback compWithReloadFunc;

    private long loadedMtime = UNINITIALIZED_MTIME;
    private String loadedHash = null;


    public JsonManagerWithConflictSafe(String json_file_path, JsonManagerCallback compWithReloadFunc) {
        this(new File(json_file_path), compWithReloadFunc);
    }

    public JsonManagerWithConflictSafe(File jsonFile, JsonManagerCallback compWithReloadFunc) {
        super(jsonFile);
        this.compWithReloadFunc = compWithReloadFunc;
        if (this.jsonObject != null) {
            initializeMetadata();
            compWithReloadFunc.actionAfterSuccessfullyOpeningJson(this);
        } else {
            compWithReloadFunc.actionAfterFailingToOpenJson(this);
        }
    }

    public void openJson(File jsonFile) {
        this.jsonFile = jsonFile;
        this.jsonObject = loadJsonObject(this.jsonFile);
        if (this.jsonObject != null) {
            initializeMetadata();
            compWithReloadFunc.actionAfterSuccessfullyOpeningJson(this);
        } else {
            compWithReloadFunc.actionAfterFailingToOpenJson(this);
        }
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(compWithReloadFunc.getFrame(), msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void initializeMetadata() {
        this.loadedMtime = getLastModifiedTime();
        this.loadedHash = getHashString();
    }

    /**
     * Save current text. If forceOverwrite==false and disk mtime differs from loadedMtime,
     * show conflict dialog (Cancel/Overwrite/Reload/Save As).
     */
    public boolean doSave(boolean forceOverwrite) {
        if (jsonFile == null) {
            System.err.println("jsonFile is null.");
            compWithReloadFunc.actionAfterFailingToSaveJson(this);
            return false;
        }

        try {
            if (!forceOverwrite && hasConflict()) {
                //return handleConflict();
                if (handleConflict()) {
                    compWithReloadFunc.actionAfterSuccessfullySavingJson(this);
                    return true;
                } else {
                    compWithReloadFunc.actionAfterFailingToSaveJson(this);
                    return false;
                }

            }
            //return saveAndUpdateMetadata();
            if (saveAndUpdateMetadata()) {
                compWithReloadFunc.actionAfterSuccessfullySavingJson(this);
                return true;
            } else {
                compWithReloadFunc.actionAfterFailingToSaveJson(this);
                return false;
            }
        } catch (IOException ex) {
            showError("Save failed: " + ex.getMessage());
            compWithReloadFunc.actionAfterFailingToSaveJson(this);
            return false;
        }
    }

    private boolean hasConflict() throws IOException {
        if (loadedMtime == UNINITIALIZED_MTIME) {
            return false;
        }
        long diskMtime = Files.getLastModifiedTime(jsonFile.toPath()).toMillis();
        return diskMtime != loadedMtime;
    }

    private boolean handleConflict() throws IOException {
        long diskMtime = Files.getLastModifiedTime(jsonFile.toPath()).toMillis();
        int choice = showConflictDialog(jsonFile.toPath(), diskMtime, loadedMtime);

        switch (choice) {
            case 0:
                return false;  // Cancel
            case 1:
                return saveAndUpdateMetadata();  // Overwrite
            case 2:
                return reloadFromDisk();  // Reload
            case 3:
                return doSaveAs();  // Save As
            default:
                return false;
        }
    }

    private boolean saveAndUpdateMetadata() throws IOException {
        if (writeJson()) {
            loadedMtime = getLastModifiedTime();
            loadedHash = getHashString();
            return true;
        } else {
            return false;
        }
    }

    public boolean doSaveAs() {
        if (jsonFile == null && this.jsonObject.isEmpty()) return false;

        JFileChooser fc = new JFileChooser();
        if (jsonFile != null) fc.setSelectedFile(jsonFile);
        if (fc.showSaveDialog(compWithReloadFunc.getFrame()) == JFileChooser.APPROVE_OPTION) {
            Path dest = fc.getSelectedFile().toPath();
            if (Files.exists(dest)) {
                int ans = JOptionPane.showConfirmDialog(compWithReloadFunc.getFrame(),
                        "既に存在します。上書きしますか？" + dest.toAbsolutePath(),
                        "別名保存の確認", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (ans != JOptionPane.YES_OPTION) return false;
            }
            this.jsonFile = dest.toFile();

            return doSave(true);
        }

        return false;
    }

    public boolean reloadFromDisk() {
        if (this.jsonFile == null) {
            compWithReloadFunc.actionAfterFailingToReloadJson(this);
            return false;
        }

        this.jsonObject = loadJsonObject(this.jsonFile);
        if(this.jsonObject != null) {
            initializeMetadata();
            compWithReloadFunc.actionAfterSuccessfullyReloadingJson(this);
            return true;
        }else{
            compWithReloadFunc.actionAfterFailingToReloadJson(this);
            return false;
        }
    }

    private int showConflictDialog(Path p, long diskMtime, long myLoadedMtime) {
        String msg = "保存しようとしているファイルは、読み込み後に外部で更新されています。\n" +
                "  ファイル: " + p.toAbsolutePath() + "\n" +
                "  読み込み時刻: " + formatTimeInJST(myLoadedMtime) + "\n" +
                "  ディスク上更新時刻: " + formatTimeInJST(diskMtime) + "\n" +
                "  どうしますか？";
        String[] options = {"キャンセル", "上書き", "リロード", "別名保存"};
        return JOptionPane.showOptionDialog(compWithReloadFunc.getFrame(), msg, "競合の検出",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0]);
    }

    public long getLastModifiedTime() {
        try {
            return Files.getLastModifiedTime(this.jsonFile.toPath()).toMillis();
        } catch (IOException e) {
            System.err.println("Failed to get the last modified time: " + e.getMessage());
            return UNINITIALIZED_MTIME;
        }
    }

    private String getHashString() {
        return Hashes.sha256(gson.toJson(this.jsonObject));
    }

    /**
     * Convert epoch milliseconds to JST (Japan Standard Time) formatted string
     *
     * @param epochMilli epoch time in milliseconds
     * @return formatted time string in JST (e.g., "2025-10-20 15:30:45 JST")
     */
    public static String formatTimeInJST(long epochMilli) {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), JST)
                .format(JST_FORMATTER);
    }

    private static class Hashes {
        static String sha256(String s) {
            try {
                java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
                byte[] dig = md.digest(s.getBytes(StandardCharsets.UTF_8));
                StringBuilder sb = new StringBuilder(dig.length * 2);
                for (byte b : dig) sb.append(String.format("%02x", b));
                return sb.toString();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void showLoadedContent() {
        SwingUtilities.invokeLater(() -> compWithReloadFunc.getFrame().setVisible(true));
    }

}
