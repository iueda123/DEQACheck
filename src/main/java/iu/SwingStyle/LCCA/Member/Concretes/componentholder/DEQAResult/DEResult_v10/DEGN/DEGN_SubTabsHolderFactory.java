package iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DEGN;

import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMember;
import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMemberFactory;

public class DEGN_SubTabsHolderFactory extends AbstCHolderMemberFactory {

  private DEGN_SubTabsHolder DEGN_SubTabsHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
    if (this.DEGN_SubTabsHolder == null) {
      this.DEGN_SubTabsHolder = new DEGN_SubTabsHolder(cholder_name, short_name, args[0]);
    }
    return DEGN_SubTabsHolder;
  }
}
