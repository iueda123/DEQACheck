package iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.WithNotebookLMPane.A_QandA_Panel;

import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.WithNotebookLMPane.WithNotebookLMPanelHolder;
import iu.SwingStyle.LCCA.Utils.ColorChangeableTextArea;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

public class A_QandA_Panel extends JPanel {

    public final String ID;
    public final WithNotebookLMPanelHolder withNotebookLMPanelHolder;

    JLabel questionLabel = new JLabel("Question");
    ColorChangeableTextArea questionField = new ColorChangeableTextArea("QUESTION");
    JButton button_SendQuestionToClipboard = new JButton("send to cb");

    JLabel answerLabel = new JLabel("Answer");
    ColorChangeableTextArea answerField = new ColorChangeableTextArea("ANSWER");
    JButton button_SendAnswerToClipboard = new JButton("send to cb");

    JButton button_MoveUpPanel = new JButton("↑");
    JButton button_MoveDownPanel = new JButton("↓");
    JButton button_AddNewPanel = new JButton("+");
    JButton button_RemovePanel = new JButton("-");

    public A_QandA_Panel(String ID, String question, String answer, WithNotebookLMPanelHolder withNotebookLMPanelHolder){
        this.ID = ID;
        this.withNotebookLMPanelHolder = withNotebookLMPanelHolder;

        questionField.setText(question);
        questionField.updateDefaultValue();
        questionField.resetBackgroundColor();
        questionField.setLineWrap(true);
        answerField.setText(answer);
        answerField.updateDefaultValue();
        answerField.resetBackgroundColor();
        answerField.setLineWrap(true);

        Box box_for_QuestionLabelAndScbButton = Box.createHorizontalBox();
        box_for_QuestionLabelAndScbButton.add(questionLabel);
        //box_for_QuestionLabelAndScbButton.add(Box.createHorizontalStrut(5));
        box_for_QuestionLabelAndScbButton.add(Box.createGlue());
        box_for_QuestionLabelAndScbButton.add(button_SendQuestionToClipboard);
        //box_for_QuestionLabelAndScbButton.add(Box.createGlue());

        JPanel questionPanel = new JPanel(new BorderLayout());
        questionPanel.add(new JLabel("Q: "), BorderLayout.NORTH);
        JScrollPane scrollPaneForQuestionField = new JScrollPane(questionField);
        scrollPaneForQuestionField.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        questionPanel.add(scrollPaneForQuestionField, BorderLayout.WEST);
        scrollPaneForQuestionField.setPreferredSize(new Dimension(600, 100));

        Box box_for_AnswerLabelAndScbButton = Box.createHorizontalBox();
        box_for_AnswerLabelAndScbButton.add(answerLabel);
        //box_for_AnswerLabelAndScbButton.add(Box.createHorizontalStrut(5));
        box_for_AnswerLabelAndScbButton.add(Box.createGlue());
        box_for_AnswerLabelAndScbButton.add(button_SendAnswerToClipboard);
        //box_for_AnswerLabelAndScbButton.add(Box.createGlue());

        JPanel answerPanel = new JPanel(new BorderLayout());
        answerPanel.add(new JLabel("A: "), BorderLayout.NORTH);
        JScrollPane scrollPaneForAnswerField = new JScrollPane(answerField);
        scrollPaneForAnswerField.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        answerPanel.add(scrollPaneForAnswerField, BorderLayout.WEST);
        scrollPaneForAnswerField.setPreferredSize(new Dimension(600, 200));

        Box box_for_PanelControlButtons = Box.createHorizontalBox();
        box_for_PanelControlButtons.add(Box.createGlue());
        box_for_PanelControlButtons.add(button_MoveUpPanel);
        box_for_PanelControlButtons.add(button_MoveDownPanel);
        box_for_PanelControlButtons.add(button_RemovePanel);
        box_for_PanelControlButtons.add(button_AddNewPanel);

        Box vBox = Box.createVerticalBox();
        vBox.setBorder(new LineBorder(Color.lightGray, 10));
        vBox.add(box_for_QuestionLabelAndScbButton);
        vBox.add(scrollPaneForQuestionField);
        vBox.add(box_for_AnswerLabelAndScbButton);
        vBox.add(scrollPaneForAnswerField);
        vBox.add(box_for_PanelControlButtons);

        this.setBorder(new TitledBorder(this.ID));
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout());
        this.add(vBox, BorderLayout.CENTER);

        button_SendQuestionToClipboard.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection selection = new StringSelection(questionField.getText());
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
            }
        });

        button_SendAnswerToClipboard.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection selection = new StringSelection(answerField.getText());
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
            }
        });

        button_MoveUpPanel.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withNotebookLMPanelHolder.moveUpPanel(ID);
            }
        });

        button_MoveDownPanel.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withNotebookLMPanelHolder.moveDewnPanel(ID);
            }
        });

        button_RemovePanel.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               withNotebookLMPanelHolder.remove_One_QandA_Panel(ID);
            }
        });

        button_AddNewPanel.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withNotebookLMPanelHolder.add_One_QandA_Panel(ID);
            }
        });



    }


    public String getQuestion() {
        return questionField.getText();
    }

    public String getAnswer() {
        return answerField.getText();
    }

    public void updateDefaultValuesAndResetBackgroundColors() {
        answerField.resetBackgroundColor();
        answerField.updateDefaultValue();
        questionField.resetBackgroundColor();
        questionField.updateDefaultValue();
    }
}
