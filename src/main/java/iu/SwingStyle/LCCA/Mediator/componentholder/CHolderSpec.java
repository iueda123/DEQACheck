package iu.SwingStyle.LCCA.Mediator.componentholder;

import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMemberFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DECAA.DECAA_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DECAA.DECAA_SubTabsHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DENM.DENM_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DENM.DENM_SubTabsHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DERCI.DERCI_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DERCI.DERCI_SubTabsHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DESC.DESC_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DESC.DESC_SubTabsHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DESI.DESI_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DESI.DESI_SubTabsHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DEGN.DEGN_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DEGN.DEGN_SubTabsHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v11.DEDC.DE_DC_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v11.DEDC.DE_DC_SubTabsHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v12.DENM2.DE_NM2_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v12.DENM2.DE_NM2_SubTabsHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Explanation.ExplanationPanelHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Explanation.ExplanationPanelHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.SummaryPane.SummaryPaneHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.SummaryPane.SummaryPaneHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.WithNotebookLMPane.WithNotebookLMPanelHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.WithNotebookLMPane.WithNotebookLMPanelHolderFactory;

public enum CHolderSpec {
    SUB_TABS_HOLDER_DESI(
            DESI_SubTabsHolderFactory.class,
            DESI_SubTabsHolder.staticMemberName,
            "sub_tabs_holder_DESI"),
    SUB_TABS_HOLDER_DESC(
            DESC_SubTabsHolderFactory.class,
            DESC_SubTabsHolder.staticMemberName,
            "sub_tabs_holder_DESC"),
    SUB_TABS_HOLDER_DERCI(
            DERCI_SubTabsHolderFactory.class,
            DERCI_SubTabsHolder.staticMemberName,
            "sub_tabs_holder_DERCI"),
    SUB_TABS_HOLDER_DENM(
            DENM_SubTabsHolderFactory.class,
            DENM_SubTabsHolder.staticMemberName,
            "sub_tabs_holder_DENM"),
    SUB_TABS_HOLDER_DECAA(
            DECAA_SubTabsHolderFactory.class,
            DECAA_SubTabsHolder.staticMemberName,
            "sub_tabs_holder_DECAA"),
    SUB_TABS_HOLDER_DEGN(
            DEGN_SubTabsHolderFactory.class,
            DEGN_SubTabsHolder.staticMemberName,
            "sub_tabs_holder_DEGN"),
    SUB_TABS_HOLDER_DEDC(
            DE_DC_SubTabsHolderFactory.class,
            DE_DC_SubTabsHolder.staticMemberName,
            "sub_tabs_holder_DEDC"),
    SUB_TABS_HOLDER_DENM2(
            DE_NM2_SubTabsHolderFactory.class,
            DE_NM2_SubTabsHolder.staticMemberName,
            "sub_tabs_holder_DENM2"),
    EXPLANATION_PANEL_HOLDER(
            ExplanationPanelHolderFactory.class,
            ExplanationPanelHolder.staticMemberName,
            "explanation panel holder"),
    WITH_NOTEBOOK_LM_PANE_HOLDER(
            WithNotebookLMPanelHolderFactory.class,
            WithNotebookLMPanelHolder.staticMemberName,
            "With NotebookLM Pane Holder"),
    SUMMARY_PANE_HOLDER(
            SummaryPaneHolderFactory.class,
            SummaryPaneHolder.staticMemberName,
            "summary pane holder");

    public final Class<? extends AbstCHolderMemberFactory> factoryClass;
    public final String memberName;
    public final String description;

    CHolderSpec(
            Class<? extends AbstCHolderMemberFactory> factoryClass,
            String memberName,
            String description) {
        this.factoryClass = factoryClass;
        this.memberName = memberName;
        this.description = description;
    }
}
