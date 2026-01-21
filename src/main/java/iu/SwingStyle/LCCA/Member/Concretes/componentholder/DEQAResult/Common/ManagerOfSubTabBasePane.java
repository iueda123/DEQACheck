package iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common;

import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.DEQAResultPane.One_DEQAResult_Pane_Abs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ManagerOfSubTabBasePane {
    JPanel basePaneForDEQAResultPanes = new JPanel();
    JPanel subTabBasePane;
    final String tabName;

    final String sectionType;
    final String sectionName;
    final String subSectionName;

    ArrayList<One_DEQAResult_Pane_Abs> dePaneArray = new ArrayList<>();

    final JTabbedPane tabbedPane;
    final NotePane notePane;
    private SubTabsHolderItrfc subTabsHolder;

    public ManagerOfSubTabBasePane(String sectionType, String tabName, String sectionName, String subSectionName, JTabbedPane tabbedPane) {
        this.basePaneForDEQAResultPanes.setLayout(new BoxLayout(this.basePaneForDEQAResultPanes, BoxLayout.Y_AXIS));
        this.sectionType = sectionType;
        this.tabName = tabName;
        this.sectionName = sectionName;
        this.subSectionName = subSectionName;
        this.tabbedPane = tabbedPane;
        this.notePane = new NotePane(tabName, sectionName, subSectionName, this.tabbedPane, this);
    }


    public void addToTheDePaneArray(One_DEQAResult_Pane_Abs oneDeResultPaneAbs) {
        this.dePaneArray.add(oneDeResultPaneAbs);
        oneDeResultPaneAbs.registerManagerOfSubTabBasePane(this);
    }

    public void clearTheDePaneArray() {
        this.dePaneArray.clear();
    }

    public JComponent constructBasePaneOfSubTab() {

        if (subTabBasePane == null) {
            subTabBasePane = new JPanel();
            subTabBasePane.setLayout(new BoxLayout(subTabBasePane, BoxLayout.Y_AXIS));

            //subTabBasePane.setBackground(Color.YELLOW); //For debug

            // NotePane を配置
            subTabBasePane.add(notePane);

            // OneDEResultPane たちを配置
            for (int i = 0; i < dePaneArray.size(); i++) {
                //basePanel.add(new JLabel(" ")); // Separator
                One_DEQAResult_Pane_Abs pane = dePaneArray.get(i);
                pane.setMaximumSize(new Dimension(Integer.MAX_VALUE, pane.getPreferredSize().height));
                pane.setAlignmentX(Component.LEFT_ALIGNMENT);
                basePaneForDEQAResultPanes.add(pane);
            }
            // DEResult Pane 達を置くパネルの全体のPreferredSizeを明示的に計算
            int totalHeight = dePaneArray.stream().mapToInt(p -> p.getPreferredSize().height).sum();
            //totalHeight += 100; // NotePane分足す
            totalHeight = Math.max(totalHeight, 1000);

            basePaneForDEQAResultPanes.setPreferredSize(new Dimension(800, totalHeight));


            subTabBasePane.add(basePaneForDEQAResultPanes);

            // スクロールパネルを用意
            JScrollPane scrollPane = new JScrollPane(basePaneForDEQAResultPanes,
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);
            scrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
            //return (scrollPane);

            // 土台を返す
            subTabBasePane.add(scrollPane);
        }

        return (subTabBasePane);

    }

    public ArrayList<One_DEQAResult_Pane_Abs> getDeqaPaneArray() {
        return dePaneArray;
    }

    public String getTabName() {
        return this.tabName;
    }

    public JPanel getBasePaneForDEQAResultPanes() {
        return basePaneForDEQAResultPanes;
    }

    public String getSectionType(){return sectionType; }

    public String getSectionName() {
        return sectionName;
    }

    public String getSubSectionName() {
        return subSectionName;
    }

    public NotePane getNotePane() {
        return notePane;
    }

    public SubTabsHolderItrfc getSubTabsHolder() {
        return subTabsHolder;
    }

    public void revalidateChildren() {
        this.basePaneForDEQAResultPanes.revalidate();
    }

    public void registerSubTabsHolder(SubTabsHolderItrfc subTabsHolder) {
        this.subTabsHolder = subTabsHolder;
    }

    public String getAuthorYear() {
        return this.subTabsHolder.getAuthorYear();
    }

    public void addExplanationToNotePane(String explanation) {
        this.notePane.setSubsetOfExplanation(explanation);
    }
}
