package ansk98.de.byteunbound.service.event;

import org.springframework.context.ApplicationEvent;

/**
 * Application event for emitting exceptions.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
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
