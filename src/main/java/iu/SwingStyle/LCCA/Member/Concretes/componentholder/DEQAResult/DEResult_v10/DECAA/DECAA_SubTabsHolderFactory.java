package iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DECAA;

import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMember;
import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMemberFactory;

public class DECAA_SubTabsHolderFactory extends AbstCHolderMemberFactory {

    private DECAA_SubTabsHolder DECAA_SubTabsHolder;

    @Override
    protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
        if (this.DECAA_SubTabsHolder == null) {
            this.DECAA_SubTabsHolder = new DECAA_SubTabsHolder(cholder_name, short_name, args[0]);
        }
        return DECAA_SubTabsHolder;
    }
}
