package kz.strongteam.strongteamnews.exceptions;

public class BadRequestException extends BaseException {
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
