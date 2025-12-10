package iu.LCAC.Member.componentholder.Concretes.DEQAResult.QAResult_v7.QACM;

import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMemberFactory;

public class QACM_SubTabsHolderFactory extends AbstCHolderMemberFactory {

  private QACM_SubTabsHolder qacm_SubTabsHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
    if (this.qacm_SubTabsHolder == null) {
      this.qacm_SubTabsHolder = new QACM_SubTabsHolder(cholder_name, short_name, args[0]);
    }
    return qacm_SubTabsHolder;
  }
}
