package iu.LCCA.Member.componentholder.Concretes.Sample.ButtonPanel;

import iu.LCCA.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCCA.Member.componentholder.Abstract.AbstCHolderMemberFactory;

public class ButtonPanelHolderFactory extends AbstCHolderMemberFactory {

  private ButtonPanelHolder buttonPanelHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
    if (this.buttonPanelHolder == null) {
      this.buttonPanelHolder = new ButtonPanelHolder(cholder_name, short_name, args);
    }
    return buttonPanelHolder;
  }
}
