package ansk98.de.byteunbound.service.api.email;

import jakarta.mail.Message;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Retrieves emails received after the specified time.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
public interface IEmailReader {

    /**
     * Finds emails that were received after the given date.
     *
     * @param dateTime time
     * @return list of received emails
     */
    List<Message> findEmailsReceivedAfter(ZonedDateTime dateTime);
}
