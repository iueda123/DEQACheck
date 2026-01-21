package iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.SummaryPane;

import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMember;
import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMemberFactory;

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
