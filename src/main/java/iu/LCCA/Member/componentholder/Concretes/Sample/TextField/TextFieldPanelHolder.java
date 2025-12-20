package iu.LCCA.Member.componentholder.Concretes.Sample.TextField;

import iu.LCCA.Mediator.action.ActionMediator;
import iu.LCCA.Mediator.componentholder.CHolderMediator;
import iu.LCCA.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCCA.Utils.FontManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TextFieldPanelHolder extends AbstCHolderMember {

  JPanel panel = new JPanel();
  JTextField SampleTextField = new JTextField("please write something.");

  public TextFieldPanelHolder(String cholder_name, String short_name, String... args) {
    super(cholder_name, short_name);




    panel.add(SampleTextField);

    SampleTextField.addPropertyChangeListener(new SamplePropertyChangeListener());
  }

  @Override
  public JComponent getBaseComponent() {
    return this.panel;
  }

  @Override
  public void postInitialize() {}

  public void setText(String text) {
    SampleTextField.setText(text);
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

  /**
   * コンポーネントのプロパティが変更された時に呼び出される。 コンポーネントのプロパティが変更されるときとは、 フォント、前景色、背景色が変更されたとき、 コンポーネント生成時などである。
   *
   * <p>起動時に行わせたい処理を書き込むと良いのだと思う。 TODO:起動時に2回呼び出されるバグ？あり。大きな問題はないので一旦放置。なんか気持ち悪い。
   */
  private class SamplePropertyChangeListener implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
      System.out.println("Property of SampleTextField has changed.");

      if (actionMediator != null) {
        System.out.println("Action Starter is not null! @" + this.getClass().toString());
        actionMediator
            .getInstanceOfAMember("initialize_sample_text_field")
            .perform(new ActionEvent(this, 0, "Property of SampleTextField has changed."));
      } else {
        System.err.println("Action Starter is null!" + "@" + this.getClass().toString());
      }
    }
  }

  private static void createAndShowGUI() {
    FontManager.setGlobalFont();

    JFrame frame = new JFrame("Member Test");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    AbstCHolderMember holder = new TextFieldPanelHolder("text_field_pane", "Text Field Panel", "");
    holder.initialize(); // Build the component before adding to frame

    frame.getContentPane().add(holder.getBaseComponent(), BorderLayout.CENTER);
    frame.pack();
    frame.setSize(400, 300);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    holder.postInitialize(); // Run post initialization after the component becomes visible
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> createAndShowGUI());
  }
}
