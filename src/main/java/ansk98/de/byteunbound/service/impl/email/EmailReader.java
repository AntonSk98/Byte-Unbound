package ansk98.de.byteunbound.service.impl.email;

import ansk98.de.byteunbound.properties.MediumNewsletterProperties;
import ansk98.de.byteunbound.service.api.email.IEmailReader;
import ansk98.de.byteunbound.service.parameter.email.EmailProperties;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Implementation of {@link IEmailReader}.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
@Component
public class EmailReader implements IEmailReader {

    private final EmailProperties emailProperties;
    private final Properties connectProperties;
    private final MediumNewsletterProperties mediumNewsletterProperties;

    public EmailReader(EmailProperties emailProperties, MediumNewsletterProperties mediumNewsletterProperties) {
        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imaps.host", emailProperties.host());
        properties.put("mail.imaps.port", emailProperties.port());
        properties.put("mail.imaps.ssl.enable", emailProperties.ssl());

        this.connectProperties = properties;
        this.emailProperties = emailProperties;
        this.mediumNewsletterProperties = mediumNewsletterProperties;
    }

    @Override
    public List<Message> findEmailsReceivedAfter(ZonedDateTime dateTime) {
        Session session = Session.getInstance(connectProperties, null);
        try {
            Store store = session.getStore("imaps");
            store.connect(emailProperties.username(), emailProperties.password());
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            return Arrays.stream(inbox.getMessages())
                    .filter(message -> getReceivedDate(message).after(Date.from(dateTime.toInstant())))
                    .filter(message -> from(message)
                            .stream()
                            .anyMatch(sender -> sender.equals(mediumNewsletterProperties.sender()))
                    )
                    .toList();
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to find received emails", e);
        }
    }

    private static Date getReceivedDate(Message message) {
        try {
            return message.getReceivedDate();
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to find received date", e);
        }
    }

    private static List<String> from(Message message) {
        try {
            return Arrays.stream(message.getFrom())
                    .map(address -> (InternetAddress) address)
                    .map(InternetAddress::getAddress)
                    .toList();
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to extract email sender", e);
        }
    }
}
