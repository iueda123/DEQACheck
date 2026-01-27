package iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult;

import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DECAA.DECAA_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DEGN.DEGN_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DENM.DENM_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DERCI.DERCI_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DESC.DESC_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DESI.DESI_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v11.DEDC.DEDC_v11_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v11.DESI.DESI_v11_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v12.DENM2.DENM_v12_SubTabsHolder;

/**
 * sub_tabs_holder_name の定義とガイドファイルのマッピング
 */
public enum SubTabsHolderConfig {
    DESI_v10(DESI_SubTabsHolder.staticMemberName, "./prompts/DE_Guide_v10_1.md"),
    DESC_v10(DESC_SubTabsHolder.staticMemberName, "./prompts/DE_Guide_v10_1.md"),
    DERCI_v10(DERCI_SubTabsHolder.staticMemberName, "./prompts/DE_Guide_v10_1.md"),
    DENM_v10(DENM_SubTabsHolder.staticMemberName, "./prompts/DE_Guide_v10_1.md"),
    DECAA_v10(DECAA_SubTabsHolder.staticMemberName, "./prompts/DE_Guide_v10_1.md"),
    DEGN_v10(DEGN_SubTabsHolder.staticMemberName, "./prompts/DE_Guide_v10_1.md"),
    DESI_v11(DESI_v11_SubTabsHolder.staticMemberName, "./prompts/DE_Guide_v11.md"),
    DEDC_v11(DEDC_v11_SubTabsHolder.staticMemberName, "./prompts/DE_Guide_v11.md"),
    DENM_v12(DENM_v12_SubTabsHolder.staticMemberName, "./prompts/DE_Guide_v12.md");

    private final String holderName;
    private final String guideFilePath;

    SubTabsHolderConfig(String holderName, String guideFilePath) {
        this.holderName = holderName;
        this.guideFilePath = guideFilePath;
    }

    public String getHolderName() {
        return holderName;
    }

    public String getGuideFilePath() {
        return guideFilePath;
    }

    /**
     * sub_tabs_holder_name から対応する SubTabsHolderConfig を取得
     *
     * @param holderName 例: "sub_tabs_holder_DESI"
     * @return 対応する SubTabsHolderConfig、見つからない場合は null
     */
    public static SubTabsHolderConfig fromHolderName(String holderName) {
        if (holderName == null) {
            return null;
        }
        for (SubTabsHolderConfig config : values()) {
            if (config.holderName.equals(holderName)) {
                return config;
            }
        }
        return null;
    }
}