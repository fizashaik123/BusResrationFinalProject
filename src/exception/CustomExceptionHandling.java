package exception;

public class CustomExceptionHandling extends RuntimeException{
    public CustomExceptionHandling(String message) {
        super(message);
    }
}
