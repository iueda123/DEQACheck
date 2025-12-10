package PrepForFsPanel.SupportingClasses;

import javax.swing.*;
import java.io.File;
import java.util.regex.Pattern;

public class FileFilterFactory_A {


    public final static String jpeg = "jpeg";
    public final static String jpg = "jpg";
    public final static String gif = "gif";
    public final static String tiff = "tiff";
    public final static String tif = "tif";
    public final static String png = "png";

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = FileFilterFactory_A.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /*
     * Get the extension of a file.
     */
    protected static String getExtension(File f) {
        if (f.getName().startsWith(".")) {
            return "";
        } else {
            String ext = null;
            String s = f.getName();
            int i = s.lastIndexOf('.');

            if (i > 0 && i < s.length() - 1) {
                ext = s.substring(i + 1).toLowerCase();
            }
            return ext;
        }
    }

    protected static String getExtension(String FileNameWithExtension) {
        if (FileNameWithExtension.startsWith(".")) {
            return "";
        } else {
            Pattern splitMark = Pattern.compile("\\.");
            String[] SplitResult = splitMark.split(FileNameWithExtension);

            /*拡張子を分離*/
            String Extension = SplitResult[SplitResult.length - 1];
            return Extension;
        }
    }

    protected static String getDoubleExtension(String FileNameWithExtension) {
        if (FileNameWithExtension.startsWith(".")) {
            return "";
        } else {
            Pattern splitMark = Pattern.compile("\\.");
            String[] split_rslt = splitMark.split(FileNameWithExtension);

            /*拡張子を分離*/
            if (split_rslt.length >= 3) {
                String Extension = split_rslt[split_rslt.length - 2] + "." + split_rslt[split_rslt.length - 1];
                return Extension;
            } else {
                return "";
            }
        }
    }
}
