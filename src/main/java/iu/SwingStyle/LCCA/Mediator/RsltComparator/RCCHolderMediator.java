package iu.SwingStyle.LCCA.Mediator.RsltComparator;

import iu.SwingStyle.LCCA.Mediator.MemberFactoryLoader;
import iu.SwingStyle.LCCA.Mediator.componentholder.CHolderMediator;
import iu.SwingStyle.LCCA.Member.MemberIntrfc;
import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMember;
import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMemberFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.MainWindow.MainWindowHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.MainWindow.MainWindowHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.StatusPanel.StatusPanelHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.RsltComparator.SideBySideComparisonHolderFactory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * RsltComparator 用の CHolderMediator。
 * CHolderMediator を継承し、createMembers をオーバーライドして
 * MainWindowHolder, StatusPanelHolder, SideBySideComparisonHolder のみを生成する。
 */
public class RCCHolderMediator extends CHolderMediator {

    /** コンストラクタの super() 呼び出し前に version を渡すための一時変数 */
    private static String pendingVersion;

    private String authorYear;
    private String version;

    private RCCHolderMediator(String authorYear) {
        super(authorYear);
        // version と authorYear は createMembers() 内で既に設定済み
    }

    /**
     * ファクトリメソッド。version を先にセットしてからコンストラクタを呼ぶ。
     */
    public static RCCHolderMediator createInstance(String authorYear, String version) {
        pendingVersion = version;
        return new RCCHolderMediator(authorYear);
    }

    @Override
    public void createMembers(String... authorYears) {
        // super() から呼ばれる。pendingVersion から version を取得。
        this.version = pendingVersion;
        this.authorYear = authorYears[0];

        AbstCHolderMemberFactory factory;

        // MainWindowHolder
        factory = MemberFactoryLoader.loadFactory(
                MainWindowHolderFactory.class.getName(),
                AbstCHolderMemberFactory.class);
        AbstCHolderMember mainWindowHolder = factory.createCHolder(
                "main_window_holder", "main window", authorYears[0]);
        mainWindowHolder.setCHolderMediator(this);
        mainWindowHolder.initialize();
        getMemberMap().put(mainWindowHolder.getMemberName(), mainWindowHolder);

        // StatusPanelHolder
        factory = MemberFactoryLoader.loadFactory(
                StatusPanelHolderFactory.class.getName(),
                AbstCHolderMemberFactory.class);
        AbstCHolderMember statusPanelHolder = factory.createCHolder(
                "status_panel_holder", "Status Panel Holder", authorYears[0]);
        statusPanelHolder.setCHolderMediator(this);
        statusPanelHolder.initialize();
        getMemberMap().put(statusPanelHolder.getMemberName(), statusPanelHolder);

        // SideBySideComparisonHolder
        factory = MemberFactoryLoader.loadFactory(
                SideBySideComparisonHolderFactory.class.getName(),
                AbstCHolderMemberFactory.class);
        AbstCHolderMember comparisonHolder = factory.createCHolder(
                "side_by_side_comparison_holder", "Side By Side Comparison",
                authorYears[0], this.version);
        comparisonHolder.setCHolderMediator(this);
        comparisonHolder.initialize();
        getMemberMap().put(comparisonHolder.getMemberName(), comparisonHolder);
    }

    /**
     * MainWindowHolder.postInitialize() は load_pane_order 等のアクションに依存するため、
     * ここでは呼ばずにタイトル設定のみ行い、SideBySideComparisonHolder の postInitialize を呼ぶ。
     */
    @Override
    public void postInitializeEachMember() {
        // MainWindowHolder: タイトルだけ設定
        MainWindowHolder mwh = (MainWindowHolder) getInstanceOfAMember("main_window_holder");
        if (mwh != null) {
            Path currentPath = Paths.get("./").toAbsolutePath().normalize();
            String currentDirName = currentPath.getFileName().toString();
            String parentDirName = currentPath.getParent().getFileName().toString();
            mwh.getMainWindow().setTitle(
                    "RsltComparator - " + authorYear + " / " + version
                            + " (" + parentDirName + "/" + currentDirName + ")");
        }

        // SideBySideComparisonHolder と StatusPanelHolder の postInitialize
        for (Map.Entry<String, MemberIntrfc> entry : getMemberMap().entrySet()) {
            if (!entry.getKey().equals("main_window_holder")) {
                ((AbstCHolderMember) entry.getValue()).postInitialize();
            }
        }
    }

    public String getVersion() {
        return version;
    }

    public String getAuthorYearStr() {
        return authorYear;
    }
}
