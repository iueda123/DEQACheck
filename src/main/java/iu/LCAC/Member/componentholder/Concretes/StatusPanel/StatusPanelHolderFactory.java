package iu.LCAC.Member.componentholder.Concretes.StatusPanel;

import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMemberFactory;

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

