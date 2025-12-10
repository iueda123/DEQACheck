package iu.LCAC.Mediator.componentholder;

public class CHolderMediatorFactory {

  private static CHolderMediator cholderMediator;

  public static CHolderMediator create(String authorYear) {
    if (cholderMediator == null) {
      cholderMediator = new CHolderMediator(authorYear);
    }
    return cholderMediator;
  }
}
