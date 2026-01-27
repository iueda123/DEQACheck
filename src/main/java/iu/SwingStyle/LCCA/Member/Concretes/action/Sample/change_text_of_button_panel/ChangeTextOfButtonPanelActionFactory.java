package iu.SwingStyle.LCCA.Member.Concretes.action.Sample.change_text_of_button_panel;

import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMember;
import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMemberFactory;

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
