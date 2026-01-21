package iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.WithNotebookLMPane;

import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMember;
import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMemberFactory;

public class WithNotebookLMPanelHolderFactory extends AbstCHolderMemberFactory {

  private WithNotebookLMPanelHolder withNotebookLMPanelHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
    if (this.withNotebookLMPanelHolder == null) {
      this.withNotebookLMPanelHolder = new WithNotebookLMPanelHolder(cholder_name, short_name, args);
    }
    return withNotebookLMPanelHolder;
  }
}
