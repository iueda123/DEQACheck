package iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DERCI;

import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMember;
import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMemberFactory;

public class DERCI_SubTabsHolderFactory extends AbstCHolderMemberFactory {

    private DERCI_SubTabsHolder DERCI_SubTabsHolder;

    @Override
    protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
        if (this.DERCI_SubTabsHolder == null) {
            this.DERCI_SubTabsHolder = new DERCI_SubTabsHolder(cholder_name, short_name, args[0]);
        }
        return DERCI_SubTabsHolder;
    }
}
