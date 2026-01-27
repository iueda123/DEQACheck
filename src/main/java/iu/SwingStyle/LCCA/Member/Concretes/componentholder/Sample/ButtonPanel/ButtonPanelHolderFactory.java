package iu.SwingStyle.LCCA.Member.Concretes.componentholder.Sample.ButtonPanel;

import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMember;
import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMemberFactory;

public class ButtonPanelHolderFactory extends AbstCHolderMemberFactory {

    private ButtonPanelHolder buttonPanelHolder;

    @Override
    protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
        if (this.buttonPanelHolder == null) {
            this.buttonPanelHolder = new ButtonPanelHolder(cholder_name, short_name, args);
        }
        return buttonPanelHolder;
    }
}
