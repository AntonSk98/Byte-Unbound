package ansk98.de.byteunbound.exception;

import ansk98.de.byteunbound.service.event.NotifiableExceptionEvent;
import org.springframework.context.ApplicationEventPublisher;

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
