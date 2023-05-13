package kz.strongteam.strongteamnews.exceptions;

public class ForbiddenException extends BaseException {
    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
