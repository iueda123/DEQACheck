package iu.LCAC.Member.componentholder.Abstract;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.MemberIntrfc;
import iu.LCAC.Utils.PropertyManager_v5;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.*;

public abstract class AbstCHolderMember implements MemberIntrfc {

  protected CHolderMediator cholderMediator;

  protected ActionMediator actionMediator;


  protected String cholderName;
  protected String shortName;

  public AbstCHolderMember(String cholderName, String shortName) {
    this.cholderName = cholderName;
    this.shortName = shortName;
  }

  public final String getShortName() {
    return shortName;
  }

  public final String getMemberName() {
    return this.cholderName;
  }

  public abstract JComponent getBaseComponent();

  /** Post initialization after the component is displayed */
  public abstract void postInitialize();

  public PropertyManager_v5 propManager;

  protected PropertyManager_v5 createPropertyManager(String property_file_path) {
    System.out.println("A property file '" + property_file_path + "' is about to load.");
    Path setting_file_path = Paths.get(property_file_path);

    if (propManager == null) {
      propManager = new PropertyManager_v5(new File(property_file_path));
    }

    //System.out.println("    Properties file '" + setting_file_path.getFileName() + "' was loaded.");

    // List up
    propManager.listUpProperty();

    return propManager;
  }
}
