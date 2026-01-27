package iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v11.DESI;

import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMember;
import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMemberFactory;

public class DESI_v11_SubTabsHolderFactory extends AbstCHolderMemberFactory {

  private DESI_v11_SubTabsHolder DESI_v11_SubTabsHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
    if (this.DESI_v11_SubTabsHolder == null) {
      this.DESI_v11_SubTabsHolder = new DESI_v11_SubTabsHolder(cholder_name, short_name, args[0]);
    }
    return DESI_v11_SubTabsHolder;
  }
}
