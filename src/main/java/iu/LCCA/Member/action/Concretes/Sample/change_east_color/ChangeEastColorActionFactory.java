package iu.LCCA.Member.action.Concretes.Sample.change_east_color;

import iu.LCCA.Member.action.Abstract.AbstActionMember;
import iu.LCCA.Member.action.Abstract.AbstActionMemberFactory;

public class ChangeEastColorActionFactory extends AbstActionMemberFactory {

  private AbstActionMember action;

  @Override
  protected AbstActionMember createInstance(String action_name, String short_name, String... args) {
    if (action == null) {
      action = new ChangeEastColorAction(action_name, short_name);
    }
    return action;
  }
}
