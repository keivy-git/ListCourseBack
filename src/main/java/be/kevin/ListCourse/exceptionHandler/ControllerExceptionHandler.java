package be.kevin.ListCourse.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Pour renvoyer un message plus clair à l'utilisateur, au lieu d'un pavé d'erreur...
 */
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * affiche une erreur sur la méthode delete
     * @param ex
     * @param request
     * @return
     */

    @ExceptionHandler(NotDeleteException.class)
    public ResponseEntity<ErrorMess> HandleNotDeleteException (NotDeleteException ex, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorMess(ex.getMessage(), request.getDescription(false)),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * affiche une erreur sur al méthode update
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(NotUpdateException.class)
    public ResponseEntity<ErrorMess> HandleNotUpdateException (NotUpdateException ex, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorMess(ex.getMessage(), request.getDescription(false)),
                HttpStatus.BAD_REQUEST);

    }
}
