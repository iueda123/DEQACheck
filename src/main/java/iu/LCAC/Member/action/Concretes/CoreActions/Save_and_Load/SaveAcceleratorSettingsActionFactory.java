package iu.LCAC.Member.action.Concretes.CoreActions.Save_and_Load;

import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.action.Abstract.AbstActionMemberFactory;

public class SaveAcceleratorSettingsActionFactory extends AbstActionMemberFactory {

  private AbstActionMember action;

  @Override
  protected AbstActionMember createInstance(String action_name, String short_name, String... args) {
    if (action == null) {
      action = new SaveAcceleratorSettingsAction(action_name, short_name);
    }
    return action;
  }
}
