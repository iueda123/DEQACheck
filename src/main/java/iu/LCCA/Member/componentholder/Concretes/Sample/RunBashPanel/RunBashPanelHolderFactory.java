package iu.LCCA.Member.componentholder.Concretes.Sample.RunBashPanel;

import iu.LCCA.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCCA.Member.componentholder.Abstract.AbstCHolderMemberFactory;

public class RunBashPanelHolderFactory extends AbstCHolderMemberFactory {

  private RunBashPanelHolder runBashPanelHolder;

  @Override
  protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
    if (this.runBashPanelHolder == null) {
      this.runBashPanelHolder = new RunBashPanelHolder(cholder_name, short_name);
    }
    return runBashPanelHolder;
  }
}
