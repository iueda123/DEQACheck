package iu.LCAC.Member.componentholder.Concretes.DEQAResult.SummaryPane;

import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMemberFactory;

public class SummaryPaneHolderFactory extends AbstCHolderMemberFactory {

  private SummaryPaneHolder summaryPaneHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
    if (this.summaryPaneHolder == null) {
      this.summaryPaneHolder = new SummaryPaneHolder(cholder_name, short_name);
    }
    return summaryPaneHolder;
  }
}
