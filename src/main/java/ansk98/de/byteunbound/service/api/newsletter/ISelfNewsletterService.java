package ansk98.de.byteunbound.service.api.newsletter;

import ansk98.de.byteunbound.domain.Article;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Service for adding article links manually via the Telegram Bot API.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
public interface ISelfNewsletterService {

    /**
     * Adds a new article link.
     *
     * @param builder article builder
     */
    void addArticle(Article.Builder builder);

    /**
     * Gets the newsletter with currently added articles and not yet published articles.
     *
     * @return newsletter
     */
    List<Article> getNewsletter();

    /**
     * Marks a newsletter as being published.
     */
    void markAsPublishedNewsletter();

    List<Article> getNewsletterPublishedAfter(ZonedDateTime dateTime);

}
