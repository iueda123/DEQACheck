package iu.LCCA.Utils.JsonManagerWithConflictSafe;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JsonManager {

    protected File jsonFile;
    protected JsonObject jsonObject;
    protected Gson gson;

    /**
     * コンストラクタ
     *
     * @param json_file_path JSONファイル
     */
    public JsonManager(String json_file_path) {
        this(new File(json_file_path));
    }

    public JsonManager(File json_file) {
        this.jsonFile = json_file;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.jsonObject = loadJsonObject(this.jsonFile);
        if(this.jsonObject == null){
            System.err.println( "this.jsonObject is null.");
        }
    }


    /* **** Public Methods **** */

    /**
     * JSONファイルを書き出す
     *
     * @return 成功した場合true
     */
    public boolean writeJson() {
        // 存在しない場合は作る
        boolean file_creation_rslt = createAFileAfterConfirmation(this.jsonFile.getAbsolutePath());

        if (file_creation_rslt) {
            // 書き込み
            try (Writer writer =
                         new OutputStreamWriter(
                                 new FileOutputStream(jsonFile), StandardCharsets.UTF_8)) {

                gson.toJson(jsonObject, writer);

                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    public File getJsonFile() {
        return jsonFile;
    }

    /**
     * パス形式のキーで値を取得する
     * 例: "reference_cohort_and_imaging/dataset_name/Answer"
     * 例: "reference_cohort_and_imaging/dataset_name/Page\\/Line" (スラッシュをエスケープ)
     *
     * @param path_key スラッシュ区切りのパス
     * @return 値（文字列）、見つからない場合はnull
     */
    public String getValueAsString(String path_key) {
        //System.out.println("path_key: " + path_key);
        // エスケープされたスラッシュを一時的にプレースホルダーに置き換える
        String placeholder = "\u0000ESCAPED_SLASH\u0000";
        path_key = path_key.replace("\\/", placeholder);

        String[] keys = path_key.split("/");
        //System.out.println("keys.length: " + keys.length);
        JsonElement current = jsonObject;

        for (String key : keys) {
            //System.out.println("key: '"+ key + "'");
            // プレースホルダーを元のスラッシュに戻す
            key = key.replace(placeholder, "/");
            // エスケープされたスペースを通常のスペースに戻す
            key = key.replace("\\ ", " ");

            // 空文字列のキーはスキップ
            if (key.isEmpty()) {
                //System.out.println("Skipping empty key");
                continue;
            }

            if (current == null || !current.isJsonObject()) {
               //System.err.println("current is null or current is not json object. @ JsonManager.java");
                return null;
            }
            current = current.getAsJsonObject().get(key);
        }

        if (current == null) {
            System.err.print("Value not found for path: " + path_key);
            System.err.println(" while reading " + jsonFile);
            return null;
        }

        if (current.isJsonPrimitive()) {
            return current.getAsString();
        } else if (current.isJsonArray()) {
            // 配列の場合は要素をセミコロン区切りで返す
            StringBuilder joined = new StringBuilder();
            current.getAsJsonArray().forEach(elem -> {
                if (joined.length() > 0) {
                    joined.append("; ");
                }
                if (elem.isJsonPrimitive()) {
                    joined.append(elem.getAsString());
                } else {
                    joined.append(elem.toString());
                }
            });
            return joined.toString();
        }

        System.err.println("Value is not a primitive type (found JsonObject or JsonArray) for path: " + path_key);
        return null;
    }

    /**
     * パス形式のキーで値を設定する
     *
     * @param path_key スラッシュ区切りのパス
     * @param value    設定する値
     */
    public boolean setValue(String path_key, String value) {
        // エスケープされたスラッシュを一時的にプレースホルダーに置き換える
        String placeholder = "\u0000ESCAPED_SLASH\u0000";
        path_key = path_key.replace("\\/", placeholder);

        String[] keys = path_key.split("/");
        JsonObject current = jsonObject;

        for (int i = 0; i < keys.length - 1; i++) {
            String key = keys[i].replace(placeholder, "/").replace("\\ ", " ");

            // 空文字列のキーはスキップ（getValueと同じ動作にする）
            if (key.isEmpty()) {
                continue;
            }

            if (!current.has(key)) {
                current.add(key, new JsonObject());
            }
            JsonElement element = current.get(key);
            if (element.isJsonObject()) {
                current = element.getAsJsonObject();
            } else {
                // 既存の値がオブジェクトでない場合、新しいオブジェクトで置き換え
                JsonObject newObj = new JsonObject();
                current.add(key, newObj);
                current = newObj;
            }
        }

        String lastKey = keys[keys.length - 1].replace(placeholder, "/").replace("\\ ", " ");
        // 最後のキーも空文字列でないことを確認
        if (lastKey.isEmpty()) {
            System.err.println("Last key is empty for path: " + path_key);
            return false;
        }

        current.addProperty(lastKey, value);

        if( this.getValueAsString(path_key).equals(value) ){
            return true;
        }else{
            System.err.println("Setting a new value was failed." + "[" + path_key + "]");
            return false;
        }
    }

    public JsonObject getJsonObject() {
        return this.jsonObject;
    }

    public String getJsonAsText(){
        return gson.toJson(this.jsonObject);
    }

    /* **** Private Method **** */

    /**
     * JSONファイルを読み込んでJsonObjectを作成する
     *
     * @return JsonObject
     */
    public JsonObject loadJsonObject(File jsonFile) {
        //if (jsonObject == null) {
            if (jsonFile.isFile()) {
                // Objectはまだないが、Fileはある時
                try (Reader reader =
                             new InputStreamReader(
                                     new FileInputStream(jsonFile), StandardCharsets.UTF_8)) {
                    JsonElement element = JsonParser.parseReader(reader);
                    if (element.isJsonObject()) {
                        jsonObject = element.getAsJsonObject();
                    } else {
                        jsonObject = new JsonObject();
                    }
                } catch (FileNotFoundException fnfe) {
                    fnfe.printStackTrace();
                    jsonObject = new JsonObject();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                    jsonObject = new JsonObject();
                }
                return jsonObject;
            } else {
                // JsonObjectもなく、JsonFileもないとき
                jsonObject = new JsonObject();
                return jsonObject;
            }
        //}
        //return jsonObject;
    }

    /**
     * ファイルが存在しない場合は作成する
     *
     * @param json_file_path_str ファイルパス
     * @return 成功した場合true
     */
    private static boolean createAFileAfterConfirmation(String json_file_path_str) {
        File json_file = new File(json_file_path_str);
        if (!json_file.exists()) {
            boolean creation_rslt = false;
            json_file.getParentFile().mkdirs();
            try {
                creation_rslt = json_file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            if (!creation_rslt) {
                return false;
            }
        }
        return true;
    }

    /* **** Test Method **** */

    public static void main(String[] args) {
        // 基本的な使い方
        /** 書き込みテスト */
        JsonManager jm = new JsonManager(new File("/tmp/test.json"));
        jm.setValue("test/key1", "value1");
        jm.setValue("test/key2", "value2");
        jm.writeJson();

        /** 読み込みテスト */
        JsonManager jm2 = new JsonManager(new File("/tmp/test.json"));
        System.out.println("key1 = " + jm2.getValueAsString("test/key1"));
        System.out.println("key2 = " + jm2.getValueAsString("test/key2"));
    }
}
