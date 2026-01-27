package iu.SwingStyle.LCCA.Member.Concretes.componentholder.Sample.TextField;

import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMember;
import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMemberFactory;

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
