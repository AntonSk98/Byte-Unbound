package ansk98.de.byteunbound.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Entity that contains a self-added article metadata in the database.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
@Entity
public class Article {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String link;

    private ZonedDateTime publishedAt;

    @Column(nullable = false)
    private boolean published;

    protected Article() {

    }

    private Article(String title, String link) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.link = link;
        this.published = false;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public boolean isPublished() {
        return published;
    }

    public ZonedDateTime getPublishedAt() {
        return publishedAt;
    }

    public void publish() {
        this.published = true;
        this.publishedAt = ZonedDateTime.now();
    }

    public static class Builder {
        private String title;
        private String link;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder link(String link) {
            this.link = link;
            return this;
        }

        public Article build() {
            return new Article(title, link);
        }
    }
}
