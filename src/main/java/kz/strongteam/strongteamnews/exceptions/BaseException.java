package kz.strongteam.strongteamnews.exceptions;

public class BaseException extends RuntimeException {

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
