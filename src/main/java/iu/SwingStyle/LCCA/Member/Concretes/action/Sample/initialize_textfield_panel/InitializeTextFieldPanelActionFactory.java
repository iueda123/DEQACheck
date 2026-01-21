package iu.SwingStyle.LCCA.Member.Concretes.action.Sample.initialize_textfield_panel;

import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMember;
import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMemberFactory;

public class InitializeTextFieldPanelActionFactory extends AbstActionMemberFactory {

  private AbstActionMember action;

  @Override
  protected AbstActionMember createInstance(String action_name, String short_name, String... args) {
    if (action == null) {
      action = new InitializeTextFieldPanelAction(action_name, short_name);
    }
    return action;
  }
}
