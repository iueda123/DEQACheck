package iu.LCCA.Member.componentholder.Concretes.DEQAResult.DEResult_v10.DESC;

import iu.LCCA.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCCA.Member.componentholder.Abstract.AbstCHolderMemberFactory;

public class DESC_SubTabsHolderFactory extends AbstCHolderMemberFactory {

  private DESC_SubTabsHolder DESC_SubTabsHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
    if (this.DESC_SubTabsHolder == null) {
      this.DESC_SubTabsHolder = new DESC_SubTabsHolder(cholder_name, short_name, args[0]);
    }
    return DESC_SubTabsHolder;
  }
}
