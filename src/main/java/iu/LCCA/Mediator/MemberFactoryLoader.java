package iu.LCCA.Mediator;

/** Utility class to instantiate factory classes via reflection. */
public final class MemberFactoryLoader {

  private MemberFactoryLoader() {
    // utility class
  }

  /**
   * Load and instantiate the specified class.
   *
   * @param className fully qualified class name of the factory. クラスの完全修飾名を単純な文字列で渡しても良い。
   * @param type expected type of the created instance
   * @param <T> type parameter of the factory
   * @return instantiated factory of given type
   */
  public static <T> T loadFactory(String className, Class<T> type) {
    try {
      Class<?> clazz = Class.forName(className);
      Object instance = clazz.getDeclaredConstructor().newInstance();
      return type.cast(instance);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    } catch (Exception e) {
      throw new RuntimeException("Failed to instantiate " + className, e);
    }
  }
}
