package be.kevin.ListCourse.exceptionHandler;

public class NotCreateException extends Exception {

    public NotCreateException() {
        super("Echec de la création !");
    }
}
