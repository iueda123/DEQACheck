package iu.SwingStyle.LCCA.Member.Concretes.action.CoreActions.Save_and_Load;

import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMember;
import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMemberFactory;

public class LoadAcceleratorSettingsActionFactory extends AbstActionMemberFactory {

  private AbstActionMember action;

  @Override
  protected AbstActionMember createInstance(String action_name, String short_name, String... args) {
    if (action == null) {
      action = new LoadAcceleratorSettingsAction(action_name, short_name);
    }
    return action;
  }
}
