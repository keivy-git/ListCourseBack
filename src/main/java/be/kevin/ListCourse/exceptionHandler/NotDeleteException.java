package be.kevin.ListCourse.exceptionHandler;

public class NotDeleteException extends Exception {



    public NotDeleteException() {
        super("Cette id n'a pas été supprimé, car elle n'existe pas !");
    }


}
