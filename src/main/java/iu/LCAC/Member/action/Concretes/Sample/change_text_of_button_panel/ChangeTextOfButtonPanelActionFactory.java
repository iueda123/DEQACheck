package iu.LCAC.Member.action.Concretes.Sample.change_text_of_button_panel;

import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.action.Abstract.AbstActionMemberFactory;

public class ChangeTextOfButtonPanelActionFactory extends AbstActionMemberFactory {

  private AbstActionMember action;

  @Override
  protected AbstActionMember createInstance(String action_name, String short_name, String... args) {
    if (action == null) {
      action = new ChangeTextOfButtonPanelAction(action_name, short_name);
    }
    return action;
  }
}
