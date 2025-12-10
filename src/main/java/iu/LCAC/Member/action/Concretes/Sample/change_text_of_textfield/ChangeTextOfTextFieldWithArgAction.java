package iu.LCAC.Member.action.Concretes.Sample.change_text_of_textfield;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Concretes.Sample.TextField.TextFieldPanelHolder;
import iu.LCAC.Utils.PropertyManager_v5;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ChangeTextOfTextFieldWithArgAction extends AbstActionMember {

    static final String SettingPropertyFilePath =
            "./settings/ActionControlledComponentFramework/settings.prop";

    public ChangeTextOfTextFieldWithArgAction(String action_name, String short_name) {
        super(action_name, short_name);
    }

    @Override
    protected void setAcceleratorKeyStroke() {
        this.getMenuItem()
                .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK));
    }

    @Override
    public void perform(ActionEvent action_event) {
        System.out.println("perform() in " + this.getClass().toString() + " was called.");

        String[] cmd_and_args = getActionCommandAndArgs(action_event, false);

        // Load Properties
        PropertyManager_v5 prop_manager = createPropertyManager(SettingPropertyFilePath);

        // Preparation of Components
        TextFieldPanelHolder text_field_panel_holder =
                (TextFieldPanelHolder) this.cholderMediator.getInstanceOfAMember("text_field_panel_holder");

        // Core
        if (text_field_panel_holder != null) {
            if (cmd_and_args.length > 1) {

                text_field_panel_holder.setText(cmd_and_args[1]);

            }
        } else {
            System.err.println("text_field_panel_holder is null.");
        }

    }

    @Override
    public void setCHolderMediator(CHolderMediator cHolderMediator) {
        this.cholderMediator = cHolderMediator;
    }

    @Override
    public void setActionMediator(ActionMediator actionMediator) {
        this.actionMediator = actionMediator;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void doWorkAsMember() {
    }
}
