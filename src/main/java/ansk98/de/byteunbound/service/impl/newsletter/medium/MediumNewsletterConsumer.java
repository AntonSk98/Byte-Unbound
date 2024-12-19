package ansk98.de.byteunbound.service.impl.newsletter.medium;

import ansk98.de.byteunbound.service.api.email.IEmailParser;
import ansk98.de.byteunbound.service.api.email.IEmailReader;
import ansk98.de.byteunbound.service.api.newsletter.INewsletterArticleBridge;
import ansk98.de.byteunbound.service.api.newsletter.INewsletterConsumer;
import ansk98.de.byteunbound.service.parameter.newsletter.MediumMailNewsletter;
import jakarta.mail.Message;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Implementation of {@link INewsletterConsumer}.
 *
 * @author Anton SKripin (anton.tech98@gmail.com)
 */
@Service
public class MediumNewsletterConsumer implements INewsletterConsumer {

    private final IEmailReader emailReader;
    private final IEmailParser emailParser;
    private final INewsletterArticleBridge newsletterArticleBridge;

    public MediumNewsletterConsumer(IEmailReader emailReader,
                                    IEmailParser emailParser,
                                    INewsletterArticleBridge newsletterArticleBridge) {
        this.emailReader = emailReader;
        this.emailParser = emailParser;
        this.newsletterArticleBridge = newsletterArticleBridge;
    }

    @Override
    public MediumMailNewsletter consumeSince(ZonedDateTime dateTime) {
        List<Message> messages = emailReader.findEmailsReceivedAfter(dateTime);
        List<String> articleLinks = messages
                .stream()
                .flatMap(message -> emailParser.extractLinks(message).stream())
                .map(newsletterArticleBridge::bridge)
                .toList();

        return new MediumMailNewsletter(articleLinks);
    }

    @Override
    public Class<?> getSource() {
        return this.getClass();
    }
}
