package iu.LCAC.Member.action.Concretes.Sample.change_text_of_textfield;

import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.action.Abstract.AbstActionMemberFactory;

public class ChangeTextOfTextFieldWithArgActionFactory extends AbstActionMemberFactory {

  private AbstActionMember action;

  @Override
  protected AbstActionMember createInstance(String action_name, String short_name, String... args) {
    if (action == null) {
      action = new ChangeTextOfTextFieldWithArgAction(action_name, short_name);
    }
    return action;
  }
}
