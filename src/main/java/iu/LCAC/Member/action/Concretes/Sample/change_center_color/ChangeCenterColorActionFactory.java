package iu.LCAC.Member.action.Concretes.Sample.change_center_color;

import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.action.Abstract.AbstActionMemberFactory;

public class ChangeCenterColorActionFactory extends AbstActionMemberFactory {

  private AbstActionMember action;

  @Override
  protected AbstActionMember createInstance(String action_name, String short_name, String... args) {
    if (action == null) {
      action = new ChangeCenterColorAction(action_name, short_name);
    }
    return action;
  }
}
