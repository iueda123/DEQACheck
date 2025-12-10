package iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult_v10.DENM;

import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMemberFactory;

public class DENM_SubTabsHolderFactory extends AbstCHolderMemberFactory {

  private DENM_SubTabsHolder DENM_SubTabsHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
    if (this.DENM_SubTabsHolder == null) {
      this.DENM_SubTabsHolder = new DENM_SubTabsHolder(cholder_name, short_name, args[0]);
    }
    return DENM_SubTabsHolder;
  }
}
