package iu.LCAC.Member.componentholder.Concretes.DEQAResult.QAResult_v7.QANM;

import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMemberFactory;

public class QANM_SubTabsHolderFactory extends AbstCHolderMemberFactory {

  private QANM_SubTabsHolder QANM_SubTabsHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
    if (this.QANM_SubTabsHolder == null) {
      this.QANM_SubTabsHolder = new QANM_SubTabsHolder(cholder_name, short_name, args[0]);
    }
    return QANM_SubTabsHolder;
  }
}
