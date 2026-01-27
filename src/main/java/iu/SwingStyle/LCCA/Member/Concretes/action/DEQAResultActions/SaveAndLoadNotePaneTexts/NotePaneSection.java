package iu.SwingStyle.LCCA.Member.Concretes.action.DEQAResultActions.SaveAndLoadNotePaneTexts;

import iu.SwingStyle.LCCA.Mediator.componentholder.CHolderMediator;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.SubTabsHolderItrfc;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DECAA.DECAA_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DEGN.DEGN_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DENM.DENM_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DERCI.DERCI_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DESC.DESC_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DESI.DESI_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v11.DEDC.DEDC_v11_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v11.DESI.DESI_v11_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v12.DENM2.DENM_v12_SubTabsHolder;

import java.util.List;

enum NotePaneSection {

    DESI_v10(DESI_SubTabsHolder.staticMemberName, "study_identification_of_de"),
    DESC_v10(DESC_SubTabsHolder.staticMemberName, "study_characteristics_of_de"),
    DERCI_v10(DERCI_SubTabsHolder.staticMemberName, "reference_cohort_and_imaging_of_de"),
    DENM_v10(DENM_SubTabsHolder.staticMemberName, "normative_modeling_of_de"),
    DECAA_v10(DECAA_SubTabsHolder.staticMemberName, "clinical_application_and_analysis_of_de"),
    DEGN_v10(DEGN_SubTabsHolder.staticMemberName, "general_notes_of_de"),
    DESI_v11(DESI_v11_SubTabsHolder.staticMemberName, "study_identification_v11_of_de"),
    DEDC_v11(DEDC_v11_SubTabsHolder.staticMemberName, "dataset_characteristics_v11_of_de"),
    DENM_v12(DENM_v12_SubTabsHolder.staticMemberName, "normative_modeling_v12_of_de");

    //QACM("sub_tabs_holder_QACM", "common_part_of_qa"),
    //QANM("sub_tabs_holder_QANM", "normative_modeling_part_of_qa"),
    //QACR("sub_tabs_holder_QACR", "clinical_research_part_of_qa");

    private final String memberId;
    private final String propFileName;

    NotePaneSection(String memberId, String propFileName) {
        this.memberId = memberId;
        this.propFileName = propFileName;
    }

    static List<NotePaneSection> deSections() {
        return List.of(DESI_v10, DESC_v10, DERCI_v10, DENM_v10, DECAA_v10, DEGN_v10, DESI_v11, DEDC_v11, DENM_v12);
    }

    static List<NotePaneSection> saveTargets() {
        return List.of(DESI_v10, DESC_v10, DERCI_v10, DENM_v10, DECAA_v10, DEGN_v10, DESI_v11, DEDC_v11, DENM_v12);
    }

    String buildPropPath(String authorYear) {
        return "./data/" + authorYear + "/NotePane/" + propFileName + ".prop";
    }

    SubTabsHolderItrfc resolveSubTabsHolder(CHolderMediator mediator) {
        return (SubTabsHolderItrfc) mediator.getInstanceOfAMember(memberId);
    }
}
