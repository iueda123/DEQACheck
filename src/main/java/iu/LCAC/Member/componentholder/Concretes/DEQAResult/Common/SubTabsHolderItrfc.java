package iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common;

import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.DEQAResultPane.One_DEQAResult_Pane_Abs;

import java.util.ArrayList;

public interface SubTabsHolderItrfc {
    String getSectionName();

    ArrayList<ManagerOfSubTabBasePane> getArrayList_of_ManagerOfSubTabBasePane();

    //panel の baseTabPane の 0 番目 の 中で一番上に配置されている OneDEResult_Pane_Abs クラスオブジェクトを取得する
    public One_DEQAResult_Pane_Abs getTheFirstJsonPanel();

    String getAuthorYear();

    
}
