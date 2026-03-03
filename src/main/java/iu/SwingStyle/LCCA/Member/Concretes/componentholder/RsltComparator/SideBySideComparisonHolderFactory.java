package iu.SwingStyle.LCCA.Member.Concretes.componentholder.RsltComparator;

import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMember;
import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMemberFactory;

public class SideBySideComparisonHolderFactory extends AbstCHolderMemberFactory {

    static AbstCHolderMember instance;

    @Override
    protected AbstCHolderMember createInstance(String cholder_name, String short_name, String... args) {
        if (instance == null) {
            // args[0] = authorYear, args[1] = version
            if ("DE_v14".equals(args[1])) {
                instance = new SideBySideComparisonHolderV14(cholder_name, short_name, args[0], args[1]);
            } else {
                instance = new SideBySideComparisonHolder(cholder_name, short_name, args[0], args[1]);
            }
        }
        return instance;
    }
}
