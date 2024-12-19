package ansk98.de.byteunbound.service.api.telegram;

import ansk98.de.byteunbound.service.parameter.telegram.Newsletter;

import java.io.InputStream;
import java.util.List;

/**
 * Service to send messages using Telegram Bot API.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
public interface ITelegramClient {

    /**
     * Sends a newsletter to the group.
     *
     * @param newsletter newsletter
     */
    void sendNewsletter(Newsletter newsletter);

    /**
     * Sends a plain message to bot.
     *
     * @param message message
     */
    void sendMessageToBot(String message);

    /**
     * Sends an article split in images in an asynchronous way.
     *
     * @param title          article title
     * @param binariesStream binary images
     */
    void sendArticleAsync(String title, List<InputStream> binariesStream);

    /**
     * Streams the file from Telegram Server by its file identifier.
     *
     * @param fileId file identifier
     * @return binary stream
     */
    InputStream streamFile(String fileId);
}
