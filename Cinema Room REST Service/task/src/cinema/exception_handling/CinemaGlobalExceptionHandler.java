package cinema.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * класс для @ExceptionHandler для всего приложения
 */
@ControllerAdvice
public class CinemaGlobalExceptionHandler {

    /**
     * когда введено некорректное место в зале
     *
     * @param exception .
     * @return статус Http ошибки и обьект для генерации обьекта ошибки error
     */
    @ExceptionHandler
    public ResponseEntity<ErrorClass> handleException(NumberException exception) {
        ErrorClass error = new ErrorClass();
        error.setError(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * когда место занято
     *
     * @param exception .
     * @return статус Http ошибки и обьект для генерации обьекта ошибки error
     */
    @ExceptionHandler
    public ResponseEntity<ErrorClass> handleException(SeatsNotNumberException exception) {
        ErrorClass error = new ErrorClass();
        error.setError(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * когда введен неверный токен
     *
     * @param exception .
     * @return статус Http ошибки и обьект для генерации обьекта ошибки error
     */
    @ExceptionHandler
    public ResponseEntity<ErrorClass> handleException(TokenNotFoundException exception) {
        ErrorClass error = new ErrorClass();
        error.setError(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * когда ввели или неввели неверный пароль для статистики
     *
     * @param exception .
     * @return статус Http ошибки и обьект для генерации обьекта ошибки error
     */
    @ExceptionHandler
    public ResponseEntity<ErrorClass> handlerException(NoRightsException exception) {
        ErrorClass error = new ErrorClass();
        error.setError(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
}
