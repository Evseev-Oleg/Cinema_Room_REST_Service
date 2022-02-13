package cinema.exception_handling;

/**
 * исключение неверного ввода
 */
public class NumberException extends RuntimeException{
    public NumberException(String message) {
        super(message);
    }
}
