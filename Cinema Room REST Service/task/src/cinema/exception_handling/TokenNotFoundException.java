package cinema.exception_handling;

/**
 * исключение не верно введенный токен для возврата билета
 */
public class TokenNotFoundException extends RuntimeException{
    public TokenNotFoundException(String message) {
        super(message);
    }
}
