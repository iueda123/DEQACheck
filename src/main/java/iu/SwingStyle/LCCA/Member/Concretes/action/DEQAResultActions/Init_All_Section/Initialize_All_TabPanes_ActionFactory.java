package iu.SwingStyle.LCCA.Member.Concretes.action.DEQAResultActions.Init_All_Section;

import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMember;
import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMemberFactory;

public class Initialize_All_TabPanes_ActionFactory extends AbstActionMemberFactory {

  private AbstActionMember action;

  @Override
  protected AbstActionMember createInstance(String action_name, String short_name, String... args) {
    if (action == null) {
      action = new Initialize_All_TabPanes_Action(action_name, short_name);
    }
    return action;
  }
}
