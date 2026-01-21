package iu.SwingStyle.LCCA.Member.Concretes.action.Sample.change_east_color;

import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMember;
import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMemberFactory;

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
