package cinema.exception_handling;

/**
 * исключение для доступа к статистике
 */
public class NoRightsException extends RuntimeException{
    public NoRightsException(String message) {
        super(message);
    }
}
