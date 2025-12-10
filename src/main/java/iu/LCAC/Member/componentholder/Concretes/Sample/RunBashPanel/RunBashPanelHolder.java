package iu.LCAC.Member.componentholder.Concretes.Sample.RunBashPanel;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RunBashPanelHolder extends AbstCHolderMember {

  JPanel panel = new JPanel();

  JButton runScriptButton = new JButton("Run A Script");

  public RunBashPanelHolder(String cholder_name, String short_name) {
    super(cholder_name, short_name);

    runScriptButton.addActionListener(
        new AbstractAction() {
          @Override
          public void actionPerformed(ActionEvent actionEvent) {
            if (actionMediator != null) {

              // 引数なし
              //actionMediator.getInstanceOfAMember("run_a_bash_script").perform(actionEvent);

              // 何かしらの文字列を引数として渡すには以下のようにしてActionEventを作成
              AbstActionMember abstActionMember = actionMediator.getInstanceOfAMember("run_a_bash_script");
              ActionEvent customEvent_with_ActionNameAndArgs = new ActionEvent(
                      this,
                      ActionEvent.ACTION_PERFORMED,
                      "DummyActionName RunBashPanelHolderから渡した引数1 " + "RunBashPanelHolderから渡した引数2 "

                      // AbstrActionMember#getActionCommandAndArgs()の自分が決めた仕様で、
                      // ActionEventオブジェクトに格納された文字列の１つ目の要素はアクション名、
                      // ２つ目以降の要素は引数扱い。
                      // なので ここでは１つ目を DummyActionName としている。

              );
              abstActionMember.perform(customEvent_with_ActionNameAndArgs);

            } else {
              System.err.println("Action Starter is null! @ " + this.getClass().toString());
            }
          }
        });

    panel.add(runScriptButton);
  }

  @Override
  public void postInitialize() {}

  @Override
  public JComponent getBaseComponent() {
    return this.panel;
  }

  public void setTextToTheButton(String text) {
    this.runScriptButton.setText(text);
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
