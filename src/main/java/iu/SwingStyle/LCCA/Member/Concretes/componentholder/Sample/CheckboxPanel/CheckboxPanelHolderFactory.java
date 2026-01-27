package iu.SwingStyle.LCCA.Member.Concretes.componentholder.Sample.CheckboxPanel;

import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMember;
import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMemberFactory;

public class CheckboxPanelHolderFactory extends AbstCHolderMemberFactory {

    private CheckboxPanelHolder checkboxPanelHolder;

    @Override
    protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
        if (this.checkboxPanelHolder == null) {
            this.checkboxPanelHolder = new CheckboxPanelHolder(cholder_name, short_name);
        }
        return checkboxPanelHolder;
    }
}
