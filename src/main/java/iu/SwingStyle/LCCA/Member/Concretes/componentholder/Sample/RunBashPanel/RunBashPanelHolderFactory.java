package iu.SwingStyle.LCCA.Member.Concretes.componentholder.Sample.RunBashPanel;

import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMember;
import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMemberFactory;

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
