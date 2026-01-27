package iu.SwingStyle.LCCA.Member.Concretes.componentholder.MainWindow;

import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMember;
import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMemberFactory;

public class MainWindowHolderFactory extends AbstCHolderMemberFactory {
    static AbstCHolderMember mainWindow;

    @Override
    protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
        if (mainWindow == null) {
            mainWindow = new MainWindowHolder(cholder_name, short_name, args[0]);
        }
        return mainWindow;
    }
}
