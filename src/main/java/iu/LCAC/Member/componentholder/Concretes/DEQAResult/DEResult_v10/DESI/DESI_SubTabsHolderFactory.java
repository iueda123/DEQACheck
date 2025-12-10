package iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult_v10.DESI;

import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMemberFactory;

public class DESI_SubTabsHolderFactory extends AbstCHolderMemberFactory {

  private DESI_SubTabsHolder DESI_SubTabsHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
    if (this.DESI_SubTabsHolder == null) {
      this.DESI_SubTabsHolder = new DESI_SubTabsHolder(cholder_name, short_name, args[0]);
    }
    return DESI_SubTabsHolder;
  }
}
