package iu.LCAC.Utils;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * The PropertyFileManager Class is a class for saving and loading of property files.
 *
 * <p>References - UTF-8 エンコーディングされたプロパティファイルを取り扱う方法 http://blog.k11i.biz/2014/09/java-utf-8.html
 */
public class PropertyIOHelper {

  public static Properties load(Path new_property_file_path) {
    Properties props = new Properties();
    try {
      InputStream is = new FileInputStream(new_property_file_path.toFile());
      InputStreamReader isr = new InputStreamReader(is, "UTF-8");
      BufferedReader bfreader = new BufferedReader(isr);
      // Reader オブジェクトを UTF-8 エンコーディング指定してInputStreamReaderを生成。
      // それをBufferで包んで、Propertiesにload()を介して渡す。
      props.load(bfreader);
    } catch (IOException ioe) {
      ioe.printStackTrace();
      System.exit(1);
    }
    return props;
  }

  public static boolean save(Properties properties, Path new_property_file_path) {
    File propfile =  new_property_file_path.toFile();
    //System.out.println("propfile: " + propfile.getAbsolutePath());
    File parent =  propfile.getParentFile();
    if(parent != null) {
      //System.out.println("parent: " + parent.getAbsolutePath());
      parent.mkdirs();
    }
    try {
      new_property_file_path.toFile().createNewFile();
      OutputStream os = new FileOutputStream(new_property_file_path.toFile());
      OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
      BufferedWriter bfwriter = new BufferedWriter(osw);
      properties.store(bfwriter, "no comment.");
      bfwriter.close();
      //System.out.println("Properties were exported to " + new_property_file_path.toString() + ".");
      return true;
    } catch (FileNotFoundException fne) {
      fne.printStackTrace();
      return false;
    } catch (UnsupportedEncodingException usee) {
      usee.printStackTrace();
      return false;
    } catch (IOException ioe) {
      ioe.printStackTrace();
      return false;
    }
  }

  public static void main(String[] args) {

    /** 読み込みテスト */
    Path property_file_path = Paths.get("src/test/resources/Demo.FileIODemo/sample.properties");
    Properties prop = PropertyIOHelper.load(property_file_path);
    System.out.println("'hello' is " + prop.get("hello"));

    /** 書き出しテスト */
    Properties another_properties = new Properties();
    another_properties.setProperty("good", "night");
    Path another_property_file_path =
        Paths.get("src/test/resources/Demo.FileIODemo/another.properties");
    PropertyIOHelper.save(another_properties, another_property_file_path);
  }
}
