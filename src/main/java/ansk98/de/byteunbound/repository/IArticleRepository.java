package ansk98.de.byteunbound.repository;

import ansk98.de.byteunbound.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Repository to manage {@link Article}.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
public interface IArticleRepository extends JpaRepository<Article, UUID> {

    /**
     * Finds all articles that were marked as published by user after the given time.
     *
     * @param dateTime time
     * @return published articles
     */
    List<Article> findByPublishedTrueAndPublishedAtAfter(ZonedDateTime dateTime);

    /**
     * Finds all articles that were not yet marked as published.
     *
     * @return all non-published articles
     */
    List<Article> findByPublishedFalse();
}
