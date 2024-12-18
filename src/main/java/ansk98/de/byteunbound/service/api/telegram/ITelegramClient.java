package ansk98.de.byteunbound.service.api.telegram;

import ansk98.de.byteunbound.service.parameter.telegram.Newsletter;

import java.io.InputStream;
import java.util.List;

public interface ITelegramClient {
    void sendNewsletter(Newsletter newsletter);

    void sendMessageToBot(String message);

    void sendArticle(String title, List<InputStream> binariesStream);

    InputStream streamFile(String fileId);
}
