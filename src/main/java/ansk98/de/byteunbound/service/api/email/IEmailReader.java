package ansk98.de.byteunbound.service.api.email;

import jakarta.mail.Message;

import java.time.ZonedDateTime;
import java.util.List;

public interface IEmailReader {

    List<Message> findEmailsReceivedAfter(ZonedDateTime dateTime);
}
