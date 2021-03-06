package be.kevin.ListCourse.exceptionHandler;

import lombok.Data;

@Data
public class ErrorMess {

    private String message;
    private String context;

    /**
     * constructeur
     * @param message
     * @param context
     */
    public ErrorMess(String message, String context) {
        this.message = message;
        this.context = context;
    }
}
