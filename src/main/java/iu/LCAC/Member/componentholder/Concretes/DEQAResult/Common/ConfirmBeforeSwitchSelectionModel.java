package iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common;

import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.DEQAResultPane.One_DEQAResult_Pane_Abs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// FromTab: updated, ToTab: no-change
//  保存再読込確認
//    Yesなら FromTab 保存再読込、ToTab 再読込、最終的にToTab選択
//    No なら FromTab 何もしない、ToTab 再読込、最終的にToTab選択
//    Cancelなら FromTab 何もしない、ToTab 何もしない、最終的にFromTab選択

// FromTab: no-change, ToTab: no-change
//    FromTab 再読込、ToTab 再読込、最終的にToTab選択

public class ConfirmBeforeSwitchSelectionModel extends DefaultSingleSelectionModel {
    private final Component parent;
    private final List<ManagerOfSubTabBasePane> managers;
    private boolean suppress = false;

    public ConfirmBeforeSwitchSelectionModel(Component parent, List<ManagerOfSubTabBasePane> managers) {
        this.parent = parent;
        this.managers = managers;
    }

    @Override
    public void setSelectedIndex(int index) {
        if (suppress) {
            super.setSelectedIndex(index);
            return;
        }

        int current = getSelectedIndex();
        if (index == current) {
            return;
        }

        // 初回（modelのselectedIndexが-1）に限り、
        // 画面上で選択されていると想定される先頭タブ(0)を「現在タブ」とみなして確認を行う。
        int effectiveCurrent = current;
        if (effectiveCurrent < 0 && !managers.isEmpty()) {
            effectiveCurrent = 0;
        }

        if (!canLeaveTab(effectiveCurrent)) {
            return;
        }

        suppress = true;
        try {
            super.setSelectedIndex(index);
        } finally {
            suppress = false;
        }

        // ToTab: 選択直後に必ずJSONを再読込
        if (index >= 0 && index < managers.size()) {
            ManagerOfSubTabBasePane target = managers.get(index);
            ArrayList<One_DEQAResult_Pane_Abs> panes = target.getDeqaPaneArray();
            for (One_DEQAResult_Pane_Abs pane : panes) {
                pane.loadJson();
            }
        }
    }

    private boolean canLeaveTab(int tabIndex) {
        if (tabIndex < 0 || tabIndex >= managers.size()) return true;

        ManagerOfSubTabBasePane manager = managers.get(tabIndex);
        ArrayList<One_DEQAResult_Pane_Abs> panes = manager.getDeqaPaneArray();
        for (One_DEQAResult_Pane_Abs pane : panes) {
            if (!pane.isUpdated()) continue;

            Object[] options = {"はい", "キャンセル"};
            int result = JOptionPane.showOptionDialog(
                    parent,
                    pane.getJsonName() + "に変更が加えられています。保存しますか？",
                    "確認",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (result == 0) { // はい
                pane.saveJson();
                pane.loadJson();
            } else { // キャンセル or 閉じる
                return false;
            }
        }

        return true;
    }
}
