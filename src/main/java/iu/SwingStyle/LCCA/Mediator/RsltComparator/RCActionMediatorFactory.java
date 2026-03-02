package iu.SwingStyle.LCCA.Mediator.RsltComparator;

public class RCActionMediatorFactory {

    private static RCActionMediator instance;

    public static RCActionMediator create(String authorYear) {
        if (instance == null) {
            instance = new RCActionMediator(authorYear);
        }
        return instance;
    }
}
