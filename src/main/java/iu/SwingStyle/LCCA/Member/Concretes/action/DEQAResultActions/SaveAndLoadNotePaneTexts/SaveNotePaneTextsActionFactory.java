package iu.SwingStyle.LCCA.Member.Concretes.action.DEQAResultActions.SaveAndLoadNotePaneTexts;

import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMember;
import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMemberFactory;

public class SaveNotePaneTextsActionFactory extends AbstActionMemberFactory {

  private AbstActionMember action;

  @Override
  protected AbstActionMember createInstance(String action_name, String short_name, String... args) {
    if (action == null) {
      action = new SaveNotePaneTextsAction(action_name, short_name, args[0]);
    }
    return action;
  }
}
