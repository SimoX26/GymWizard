package ispwproject.gymwizard.util.exception;

public class SceneLoadingException extends RuntimeException {

  public SceneLoadingException(String message) {
    super(message);
  }

  public SceneLoadingException(String message, Throwable cause) {
    super(message, cause);
  }
}
