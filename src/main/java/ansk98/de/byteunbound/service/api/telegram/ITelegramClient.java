package ansk98.de.byteunbound.service.api.telegram;

import ansk98.de.byteunbound.service.parameter.telegram.Newsletter;

public interface ITelegramClient {
    void sendNewsletter(Newsletter newsletter);

    void sendMessageToBot(String message);
}
