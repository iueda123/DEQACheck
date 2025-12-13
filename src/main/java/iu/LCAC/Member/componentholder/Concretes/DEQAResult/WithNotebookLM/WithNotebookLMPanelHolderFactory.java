package iu.LCAC.Member.componentholder.Concretes.DEQAResult.WithNotebookLM;

import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMemberFactory;
import iu.LCAC.Member.componentholder.Concretes.Sample.ButtonPanel.ButtonPanelHolder;

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
