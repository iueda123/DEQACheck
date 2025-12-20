package iu.LCCA.Member.componentholder.Concretes.Sample.TextField;

import iu.LCCA.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCCA.Member.componentholder.Abstract.AbstCHolderMemberFactory;

public class TextFieldPanelHolderFactory extends AbstCHolderMemberFactory {

  private TextFieldPanelHolder textFieldPanelHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
    if (this.textFieldPanelHolder == null) {
      this.textFieldPanelHolder = new TextFieldPanelHolder(cholder_name, short_name);
    }
    return textFieldPanelHolder;
  }
}
