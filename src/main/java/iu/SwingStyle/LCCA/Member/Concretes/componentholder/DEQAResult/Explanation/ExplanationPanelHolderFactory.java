package iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Explanation;

import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMember;
import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMemberFactory;

public class ExplanationPanelHolderFactory extends AbstCHolderMemberFactory {

  private ExplanationPanelHolder textFieldPanelHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
    if (this.textFieldPanelHolder == null) {
      this.textFieldPanelHolder = new ExplanationPanelHolder(cholder_name, short_name);
    }
    return textFieldPanelHolder;
  }
}
