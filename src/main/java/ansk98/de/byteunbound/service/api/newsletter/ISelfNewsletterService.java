package ansk98.de.byteunbound.service.api.newsletter;

import ansk98.de.byteunbound.domain.Article;

import java.time.ZonedDateTime;
import java.util.List;

public interface ISelfNewsletterService {

    void addArticle(Article.Builder builder);

    List<Article> getNewsletter();

    void markAsPublishedNewsletter();

    List<Article> getNewsletterPublishedAfter(ZonedDateTime dateTime);

}
