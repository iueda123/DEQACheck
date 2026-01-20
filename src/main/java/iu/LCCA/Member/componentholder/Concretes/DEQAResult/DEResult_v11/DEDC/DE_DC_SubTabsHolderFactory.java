package iu.LCCA.Member.componentholder.Concretes.DEQAResult.DEResult_v11.DEDC;

import iu.LCCA.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCCA.Member.componentholder.Abstract.AbstCHolderMemberFactory;

public class DE_DC_SubTabsHolderFactory extends AbstCHolderMemberFactory {

  private DE_DC_SubTabsHolder de_DC_SubTabsHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
    if (this.de_DC_SubTabsHolder == null) {
      this.de_DC_SubTabsHolder = new DE_DC_SubTabsHolder(cholder_name, short_name, args[0]);
    }
    return de_DC_SubTabsHolder;
  }
}
