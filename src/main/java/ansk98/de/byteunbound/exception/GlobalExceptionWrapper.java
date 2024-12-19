package ansk98.de.byteunbound.exception;

import ansk98.de.byteunbound.service.event.NotifiableExceptionEvent;
import org.springframework.context.ApplicationEventPublisher;

/**
 * Global exception handler that publishes a {@link NotifiableExceptionEvent} upon an exception.
 * Event listeners handle the exception asynchronously.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
public class GlobalExceptionWrapper {

    public static void wrap(ApplicationEventPublisher eventPublisher, Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            eventPublisher.publishEvent(new NotifiableExceptionEvent(e));
            throw e;
        }
    }
}
