package PrepForFsPanel.SupportingClasses;

public class SystemPropertyManager {


    /**
     * Character that separates components of a file path. This is "/" on UNIX and "\" on Windows.
     */
    public static String getFileSeparator() {
        return System.getProperty("file.separator");
    }


    /**
     * Path used to find directories and JAR archives containing class files. Elements of the class path are separated by a platform-specific character specified in the path.separator property.
     */
    public static String getJavaClassPath() {
        return System.getProperty("java.class.path");
    }


    /**
     * Installation directory for Java Runtime Environment (JRE)
     */
    public static String getJavaHome() {
        return System.getProperty("java.home");
    }


    /**
     * JRE vendor name
     */
    public static String getJavaVendor() {
        return System.getProperty("java.vendor");
    }


    /**
     * JRE vendor URL
     */
    public static String getJavaVendorUrl() {
        return System.getProperty("java.vendor.url");
    }


    /**
     * JRE version number
     */
    public static String getJavaVersion() {
        return System.getProperty("java.version");
    }


    /**
     * Sequence used by operating system to separate lines in text files
     */
    public static String getLineSeparator() {
        return System.getProperty("line.separator");
    }


    /**
     * Operating system architecture
     */
    public static String getOsArch() {
        return System.getProperty("os.arch");
    }

    /**
     * Operating system name
     *
     */
    public static String getOsName() {
        return System.getProperty("os.name");
    }

    /**
     * Operating system version
     */
    public static String getOsVersion() {
        return System.getProperty("os.version");
    }

    /**
     * Path separator character used in java.class.path
     */
    public static String getPathSeparator() {
        return System.getProperty("path.separator");
    }

    /**
     * User working directory
     */
    public static String getUserDir() {
        return System.getProperty("user.dir");
    }

    /**
     * User home directory
     */
    public static String getUserHome() {
        return System.getProperty("user.home");
    }


    /**
     * User account name
     */
    public static String getUserName() {
        return System.getProperty("user.name");
    }

}
