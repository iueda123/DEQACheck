package iu.LCCA.Member.action.Concretes.Sample.initialize_textfield_panel;

import iu.LCCA.Member.action.Abstract.AbstActionMember;
import iu.LCCA.Member.action.Abstract.AbstActionMemberFactory;

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
