package iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v12.DENM2;

import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMember;
import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMemberFactory;

public class DENM_v12_SubTabsHolderFactory extends AbstCHolderMemberFactory {

    private DENM_v12_SubTabsHolder DENMv12_SubTabsHolder;

    @Override
    protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
        if (this.DENMv12_SubTabsHolder == null) {
            this.DENMv12_SubTabsHolder = new DENM_v12_SubTabsHolder(cholder_name, short_name, args[0]);
        }
        return DENMv12_SubTabsHolder;
    }
}
