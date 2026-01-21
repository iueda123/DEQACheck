package iu.SwingStyle.LCCA.Member.Concretes.action.DEQAResultActions.SaveAndLoadNotePaneTexts;

import iu.SwingStyle.LCCA.Mediator.componentholder.CHolderMediator;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.SubTabsHolderItrfc;

import java.util.List;

enum NotePaneSection {

    DESI("sub_tabs_holder_DESI", "study_identification_of_de"),
    DESC("sub_tabs_holder_DESC", "study_characteristics_of_de"),
    DERCI("sub_tabs_holder_DERCI", "reference_cohort_and_imaging_of_de"),
    DENM("sub_tabs_holder_DENM", "normative_modeling_of_de"),
    DECAA("sub_tabs_holder_DECAA", "clinical_application_and_analysis_of_de"),
    DEGN("sub_tabs_holder_DEGN", "general_notes_of_de"),
    DENM2("sub_tabs_holder_DENM2", "normative_modeling_2nd_of_de");

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
        return List.of(DESI, DESC, DERCI, DENM, DECAA, DEGN);
    }

    static List<NotePaneSection> saveTargets() {
        //return List.of(DESI, DESC, DERCI, DENM, DECAA, DEGN, QACM, QANM, QACR);
        return List.of(DESI, DESC, DERCI, DENM, DECAA, DEGN, DENM2);
    }

    String buildPropPath(String authorYear) {
        return "./data/" + authorYear + "/NotePane/" + propFileName + ".prop";
    }

    SubTabsHolderItrfc resolveSubTabsHolder(CHolderMediator mediator) {
        return (SubTabsHolderItrfc) mediator.getInstanceOfAMember(memberId);
    }
}
