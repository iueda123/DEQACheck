package iu.SwingStyle.LCCA.Mediator.RsltComparator;

public class RCCHolderMediatorFactory {

    private static RCCHolderMediator instance;

    public static RCCHolderMediator create(String authorYear, String version) {
        if (instance == null) {
            instance = RCCHolderMediator.createInstance(authorYear, version);
        }
        return instance;
    }
}
