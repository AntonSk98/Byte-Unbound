package ansk98.de.byteunbound.service.api.email;

import jakarta.mail.Message;

import java.util.List;

/**
 * Parse that extract article link from an email message.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
public interface IEmailParser {

    /**
     * Extract the article links from the email.
     *
     * @param message email message
     * @return article links
     */
    List<String> extractLinks(Message message);
}
