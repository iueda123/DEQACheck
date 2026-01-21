package iu.SwingStyle.LCCA.Member.Concretes.componentholder.StatusPanel;

import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMember;
import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMemberFactory;

public class StatusPanelHolderFactory extends AbstCHolderMemberFactory {

    static AbstCHolderMember statusPanel;

    @Override
    protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
        if (statusPanel == null) {
            statusPanel = new StatusPanelHolder(cholder_name, short_name);
        }
        return statusPanel;
    }
}

