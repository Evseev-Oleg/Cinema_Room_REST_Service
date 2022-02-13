package cinema.exception_handling;

/**
 * исключение "место занято"
 */
public class SeatsNotNumberException extends RuntimeException{
    public SeatsNotNumberException(String message) {
        super(message);
    }
}
