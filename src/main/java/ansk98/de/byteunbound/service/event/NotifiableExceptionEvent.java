package ansk98.de.byteunbound.service.event;

import org.springframework.context.ApplicationEvent;

public class NotifiableExceptionEvent extends ApplicationEvent {

    private final Exception exception;

    public NotifiableExceptionEvent(Exception exception) {
        super(exception);
        this.exception = exception;
    }

    public String getExceptionMessage() {
        return exception.getMessage();
    }
}
