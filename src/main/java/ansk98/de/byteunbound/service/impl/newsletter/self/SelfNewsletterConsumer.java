package ansk98.de.byteunbound.service.impl.newsletter.self;

import ansk98.de.byteunbound.service.api.newsletter.INewsletterConsumer;
import ansk98.de.byteunbound.service.api.newsletter.ISelfNewsletterService;
import ansk98.de.byteunbound.service.parameter.newsletter.IAbstractNewsletter;
import ansk98.de.byteunbound.service.parameter.newsletter.SelfTelegramNewsletter;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

/**
 * Implementation of {@link INewsletterConsumer}.
 *
 * @author Anton SKripin (anton.tech98@gmail.com)
 */
@Service
public class SelfNewsletterConsumer implements INewsletterConsumer {

    private final ISelfNewsletterService newsletterService;

    public SelfNewsletterConsumer(ISelfNewsletterService newsletterService) {
        this.newsletterService = newsletterService;
    }

    @Override
    public IAbstractNewsletter consumeSince(ZonedDateTime dateTime) {
        var publishedArticles = newsletterService.getNewsletterPublishedAfter(dateTime)
                .stream()
                .map(article -> new SelfTelegramNewsletter.SelfArticle(article.getTitle(), article.getLink()))
                .toList();

        return new SelfTelegramNewsletter(publishedArticles);
    }

    @Override
    public Class<?> getSource() {
        return this.getClass();
    }
}
