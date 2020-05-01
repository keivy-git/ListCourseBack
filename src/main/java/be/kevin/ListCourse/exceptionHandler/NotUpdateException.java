package be.kevin.ListCourse.exceptionHandler;

public class NotUpdateException extends Exception {

    public NotUpdateException() {
        super("Cette id n'a pas été update, car elle n'existe pas !");
    }

}
