package iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v12.DENM2;

import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMember;
import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMemberFactory;

public class DE_NM2_SubTabsHolderFactory extends AbstCHolderMemberFactory {

  private DE_NM2_SubTabsHolder de_NM2_SubTabsHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
    if (this.de_NM2_SubTabsHolder == null) {
      this.de_NM2_SubTabsHolder = new DE_NM2_SubTabsHolder(cholder_name, short_name, args[0]);
    }
    return de_NM2_SubTabsHolder;
  }
}
