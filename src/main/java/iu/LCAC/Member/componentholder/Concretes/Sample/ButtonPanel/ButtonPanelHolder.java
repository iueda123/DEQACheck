package iu.LCAC.Member.componentholder.Concretes.Sample.ButtonPanel;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class ButtonPanelHolder extends AbstCHolderMember {

  JPanel panel = new JPanel();

  JButton sampleButton = new JButton("Button");

  public ButtonPanelHolder(String cholder_name, String short_name) {
    super(cholder_name, short_name);

    sampleButton.addActionListener(
        new AbstractAction() {
          @Override
          public void actionPerformed(ActionEvent actionEvent) {
            if (actionMediator != null) {
              actionMediator.getInstanceOfAMember("change_color_of_center").perform(actionEvent);
            } else {
              System.err.println("Action Starter is null! @ " + this.getClass().toString());
            }
          }
        });

    panel.add(sampleButton);
  }

  @Override
  public void postInitialize() {}

  @Override
  public JComponent getBaseComponent() {
    return this.panel;
  }

  public void setTextToTheButton(String text) {
    this.sampleButton.setText(text);
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
  public void initialize() {}

  @Override
  public void doWorkAsMember() {}
}
