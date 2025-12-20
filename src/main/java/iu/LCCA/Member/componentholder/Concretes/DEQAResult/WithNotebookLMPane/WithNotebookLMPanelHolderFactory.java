package iu.LCCA.Member.componentholder.Concretes.DEQAResult.WithNotebookLMPane;

import iu.LCCA.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCCA.Member.componentholder.Abstract.AbstCHolderMemberFactory;

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
