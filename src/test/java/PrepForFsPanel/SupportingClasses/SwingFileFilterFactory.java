package PrepForFsPanel.SupportingClasses;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.filechooser.FileFilter;
import java.io.File;


public class SwingFileFilterFactory extends FileFilterFactory_A {


    /**
     * Swing用ファイルフィルタを生成する。
     * 指定する拡張子（.zip）であるファイルだけを取得します。
     *
     * @param extension
     * @param description:例えば「ZIPファイル(*.zip)」などと入れる。
     * @return
     */
    public static FileFilter createFilterFromExtension(String extension, String description) {
        final String _extension = extension;
        final String _description = description;

        return new FileFilter() {

            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) return true; // これを行わないと他のディレクトリーに移れない
                String file_extension = getExtension(f.getAbsolutePath());
                int ret = file_extension.compareToIgnoreCase(_extension);
                if (ret == 0) {
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public String getDescription() {
                return _description;
            }
        };
    }

    /**
     * Swing用ファイルフィルタを生成する。フォルダだけ取得する。
     * @return
     */
    public static FileFilter createFilterForDirectory() {
        return new FileFilter() {

            String description = "folder";

            /**
             * Whether the given file is accepted by this filter.
             *
             * @param f
             */
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) return true; // これを行わないと他のディレクトリーに移れない
                return false;
            }

            /**
             * The description of this filter. For example: "JPG and GIF Images"
             */
            @Override
            public String getDescription() {
                return description;
            }
        };
    }

    /**
     * Swing用ファイルフィルタを生成する。画像ファイル(gif, jpg)だけ取得する。
     * @return
     */
    public static FileFilter createFilterForImages(){
        return new FileFilter() {

            String[] extensions = {"gif", "jpg"};
            String description = "image file";

            /**
             * Whether the given file is accepted by this filter.
             *
             * @param f
             */
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) return true; // これを行わないと他のディレクトリーに移れない
                String name = f.getName().toLowerCase();
                for (int i = 0; i < extensions.length; i++) {
                    if (name.endsWith(extensions[i])) {
                        return true;
                    }
                }
                return false;
            }

            /**
             * The description of this filter. For example: "JPG and GIF Images"
             */
            @Override
            public String getDescription() {
                return description;
            }
        };
    }


}
