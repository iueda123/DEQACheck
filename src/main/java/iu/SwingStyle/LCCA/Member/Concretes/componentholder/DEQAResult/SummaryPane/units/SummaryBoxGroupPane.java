package iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.SummaryPane.units;

import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.ManagerOfSubTabBasePane;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class SummaryBoxGroupPane extends JPanel {

    JLabel sectionNameLabel = new JLabel();

    ArrayList<ManagerOfSubTabBasePane> arrayOf_ManagerOfSubTabBasePane;
    ArrayList<String> subSectionNames = new ArrayList<>();
    TreeMap<String, JLabel> statusSquares = new TreeMap<>();

    public SummaryBoxGroupPane(ArrayList<ManagerOfSubTabBasePane> arrayOf_ManagerOfSubTabBasePane) {
        this.arrayOf_ManagerOfSubTabBasePane = arrayOf_ManagerOfSubTabBasePane;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        String sectionName = this.arrayOf_ManagerOfSubTabBasePane.get(0).getSectionName();


        //JLabel幅を固定し、表示文字列を右寄せにする
        sectionNameLabel.setText(sectionName + ": ");
        sectionNameLabel.setPreferredSize(new Dimension(300, 25));
        sectionNameLabel.setMinimumSize(new Dimension(300, 25));
        sectionNameLabel.setMaximumSize(new Dimension(300, 25));
        sectionNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(sectionNameLabel);

        for (ManagerOfSubTabBasePane managerOfSubTabBasePane : this.arrayOf_ManagerOfSubTabBasePane) {
            //System.out.println("★");
            //subSectionNames.add(managerOfSubTabBasePane.getSectionName() + "_" + managerOfSubTabBasePane.getSubSectionName());
            subSectionNames.add(managerOfSubTabBasePane.getSubSectionName());
            statusSquares.put(managerOfSubTabBasePane.getSubSectionName(), new JLabel("□"));

        }

        //JOptionPane.showMessageDialog(null, this.arrayOf_ManagerOfSubTabBasePane.size());

        for (String key : subSectionNames) {
            JLabel label = statusSquares.get(key);
            add(label);
        }

        add(Box.createHorizontalGlue());

    }

    public void setStatus(String subSectionName, String status, String answer_value) {
        statusSquares.get(subSectionName).setText(status);
        statusSquares.get(subSectionName).setToolTipText(answer_value);
    }
}
