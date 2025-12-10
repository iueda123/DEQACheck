package iu.LCAC.Member.componentholder.Concretes.Sample.CheckboxPanel;

import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMemberFactory;

public class CheckboxPanelHolderFactory extends AbstCHolderMemberFactory {

  private CheckboxPanelHolder checkboxPanelHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
    if (this.checkboxPanelHolder == null) {
      this.checkboxPanelHolder = new CheckboxPanelHolder(cholder_name, short_name);
    }
    return checkboxPanelHolder;
  }
}
