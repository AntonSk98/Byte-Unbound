package ansk98.de.byteunbound.repository;

import ansk98.de.byteunbound.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public interface IArticleRepository extends JpaRepository<Article, UUID> {

    List<Article> findByPublishedTrueAndPublishedAtAfter(ZonedDateTime dateTime);

    List<Article> findByPublishedFalse();
}
