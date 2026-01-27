package iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.QAResult_v7.QACR;

import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMember;
import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMemberFactory;

public class QACR_SubTabsHolderFactory extends AbstCHolderMemberFactory {

    private QACR_SubTabsHolder qacr_SubTabsHolder;

    @Override
    protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
        if (this.qacr_SubTabsHolder == null) {
            this.qacr_SubTabsHolder = new QACR_SubTabsHolder(cholder_name, short_name, args[0]);
        }
        return qacr_SubTabsHolder;
    }
}
