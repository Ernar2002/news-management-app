package kz.strongteam.strongteamnews.exceptions;

public class NotFoundException extends BaseException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}