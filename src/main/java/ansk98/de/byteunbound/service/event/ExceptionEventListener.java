package ansk98.de.byteunbound.service.event;

import ansk98.de.byteunbound.service.api.telegram.ITelegramClient;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class ExceptionEventListener {

    private final ITelegramClient telegramClient;

    ExceptionEventListener(ITelegramClient telegramClient) {
        this.telegramClient = telegramClient;
    }

    @EventListener
    void onEvent(NotifiableExceptionEvent exceptionEvent) {
        telegramClient.sendMessageToBot("Unexpected error occurred... Error: " + exceptionEvent.getExceptionMessage());
    }
}
