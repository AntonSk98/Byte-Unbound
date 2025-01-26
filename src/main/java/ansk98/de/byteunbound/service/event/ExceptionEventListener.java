package ansk98.de.byteunbound.service.event;

import ansk98.de.byteunbound.service.api.telegram.ITelegramClient;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Event listener that handler emitted exceptions.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
@Component
class ExceptionEventListener {

    private final ITelegramClient telegramClient;

    ExceptionEventListener(ITelegramClient telegramClient) {
        this.telegramClient = telegramClient;
    }

    /**
     * Listener on exception.
     *
     * @param exceptionEvent exception event
     */
    @EventListener
    void onEvent(NotifiableExceptionEvent exceptionEvent) {
        telegramClient.sendMessageToBot("The following error occurred: " + exceptionEvent.getExceptionMessage());
    }
}
