package iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Explanation;

/**
 * sub_tabs_holder_name の定義とガイドファイルのマッピング
 */
public enum SubTabsHolderConfig {
    DESI("sub_tabs_holder_DESI", "./prompts/DE_Guide_v10_1.md"),
    DESC("sub_tabs_holder_DESC", "./prompts/DE_Guide_v10_1.md"),
    DERCI("sub_tabs_holder_DERCI", "./prompts/DE_Guide_v10_1.md"),
    DENM("sub_tabs_holder_DENM", "./prompts/DE_Guide_v10_1.md"),
    DECAA("sub_tabs_holder_DECAA", "./prompts/DE_Guide_v10_1.md"),
    DEGN("sub_tabs_holder_DEGN", "./prompts/DE_Guide_v10_1.md"),
    DEDC("sub_tabs_holder_DEDC", "./prompts/DE_Guide_v11.md"),
    DENM2("sub_tabs_holder_DENM2", "./prompts/DE_Guide_v12.md");
    //QACM("sub_tabs_holder_QACM", "./settings/Guides/QA_Guide_v7_2.md"),
    //QANM("sub_tabs_holder_QANM", "./settings/Guides/QA_Guide_v7_2.md"),
    //QACR("sub_tabs_holder_QACR", "./settings/Guides/QA_Guide_v7_2.md");

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