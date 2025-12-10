package iu.LCAC.Utils;

/** UTF-8 エンコーディングされたプロパティファイルを取り扱う方法 http://blog.k11i.biz/2014/09/java-utf-8.html */
import java.io.*;
import java.util.Properties;
import java.util.Set;

public class PropertyManager_v5 {

  private File PropertyFile;
  private Properties PropertiesObject;

  /**
   * コンストラクタ
   *
   * @throws IOException
   */
  public PropertyManager_v5(File prop_file) {
    this.PropertyFile = prop_file;
    this.PropertiesObject = createPropertiesObject();
  }

  /* **** Public Methods **** */

  public boolean writeoutProperties() {
    // 存在しない場合は作る
    boolean file_creation_rslt = createAFileAfterConfrimation(this.PropertyFile.getAbsolutePath());

    if (file_creation_rslt == true) {
      // 書き込み
      boolean rslt = PropertyIOHelper.save(this.PropertiesObject, this.PropertyFile.toPath());
      return rslt;
    } else {
      return file_creation_rslt;
    }
  }

  public File getPropertyFile() {
    return PropertyFile;
  }

  public String getValueOrCreateNew(String key) {
    if (!PropertiesObject.stringPropertyNames().contains(key)) {
      // 登録されているPropertiesObjectに目的のキーがない時
      // JOptionPane.showMessageDialog(null, "Can't get a property value of '" + key + "'.");

      PropertiesObject.setProperty(key, ""); // 空の値を入れた、新しいプロパティ作って、修正しやすくする。
      writeoutProperties();
      // JOptionPane.showMessageDialog(null, "A property key '" + key + "' was added to the current
      // property object\n" +
      //        " and write out to '" + PropertyFile.getAbsolutePath() + "'.\n" +
      //        "Please open the file and edit it.");
      // JOptionPane.showMessageDialog(null, "This application will be closed.");

      // Propertyファイルを保持するフォルダを開く
      OpenWithFileExplorer.exploreParent(PropertyFile.getAbsolutePath());

      System.exit(1);
      return "";
    } else {
      // 登録されているPropertiesObjectに目的のキーがある時
      return PropertiesObject.getProperty(key);
    }
  }

  public String getValue(String key) {
    return PropertiesObject.getProperty(key);
  }

  public void setProperty(String key, String val) {
    this.PropertiesObject.setProperty(key, val);
  }

  public Properties getProperty() {
    return this.PropertiesObject;
  }

  public void listUpProperty() {
    //System.out.println("");
    Set<String> property_names = this.PropertiesObject.stringPropertyNames();
    String value = "";
    //System.out.println("    ================================");
    //System.out.println("    property_name -> property_value");
    //System.out.println("    --------------------------------");
    for (String property_name : property_names) {
      value = (String) this.PropertiesObject.get(property_name);
      //System.out.println("    " + property_name + " -> " + value);
    }
    //System.out.println("    ================================");
    //System.out.println("");
  }

  /* **** Private Method **** */

  private Properties createPropertiesObject() {
    if (PropertiesObject == null) {
      if (PropertyFile.isFile()) {
        // Objectはまだないが、Fileはある時。
        InputStream is = null;
        try {
          is = new FileInputStream(PropertyFile);
          InputStreamReader isr = new InputStreamReader(is, "UTF-8");
          BufferedReader bfreader = new BufferedReader(isr);
          // Properties#load() で渡す Reader オブジェクトを
          // UTF-8 エンコーディング指定して生成したInputStreamReader オブジェクトにする
          PropertiesObject = new Properties();
          PropertiesObject.load(bfreader);
          bfreader.close();
          isr.close();
          is.close();
        } catch (FileNotFoundException fnfe) {
          fnfe.printStackTrace();
        } catch (UnsupportedEncodingException uee) {
          uee.printStackTrace();
        } catch (IOException ioe) {
          ioe.printStackTrace();
        }
        return PropertiesObject;
      } else {
        // PropertiesObjectもなく、PropertiesFileもないとき
        Properties empty_properties_object = new Properties();
        // empty_properties_object.setProperty("name", "value");//空の値を入れた、新しいプロパティ作って、修正しやすくする。
        this.PropertiesObject = empty_properties_object;

        return this.PropertiesObject;
      }
    }
    return PropertiesObject;
  }

  /* **** Test Method **** */

  public static void main(String[] args) {
    // 基本的な使い方
    /** 書き込みテスト */
    PropertyManager_v5 pm = new PropertyManager_v5(new File("/tmp/settings/property_test.prop"));
    pm.setProperty("x", "224");
    pm.writeoutProperties();

    /** 読み込みテスト */
    PropertyManager_v5 pm2 = new PropertyManager_v5(new File("/tmp/settings/property_test.prop"));
    System.out.println("x = " + pm2.getValue("x"));

    /** 存在しない値の読み込み */
    System.out.println("y = " + pm2.getValueOrCreateNew("y"));
  }

  private static boolean createAFileAfterConfrimation(String property_file_path_str) {
    File property_file = new File(property_file_path_str);
    if (!property_file.exists()) {
      Object[] options = {"Yes", "No"};
      String message =
          "A property file '"
              + property_file.getAbsolutePath()
              + "' does not exist. \nDo you want to create it?";
      String title = "Confirmation";
      // int answer = JOptionPane.showOptionDialog(null, message, title, JOptionPane.YES_NO_OPTION,
      // JOptionPane.QUESTION_MESSAGE,
      //        null,     //do not use a custom Icon
      //        options,  //the titles of buttons
      //        options[1]); //default button title
      // if (answer == 1) {
      //    //System.exit(1);
      //    return false;
      // } else {
      boolean creation_rslt = false;
      creation_rslt = property_file.getParentFile().mkdirs();
      try {
        creation_rslt = property_file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
        // System.exit(1);
        return false;
      }
      if (creation_rslt) {
        // JOptionPane.showMessageDialog(null, "A property file '" + property_file.getAbsolutePath()
        // + "' was created.");
      } else {
        // JOptionPane.showMessageDialog(null, "A property file '" + property_file.getAbsolutePath()
        // + "' was not able to create.");
        // System.exit(1);
        return false;
      }
      // }
    }
    return true;
  }

  public Set<String> stringPropertyNames() {
    return this.PropertiesObject.stringPropertyNames();
  }
}
