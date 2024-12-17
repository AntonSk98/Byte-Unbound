package ansk98.de.byteunbound.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@Entity
public class NewsletterRegistry {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String source;

    private ZonedDateTime lastScannedAt;

    protected NewsletterRegistry() {
    }

    private NewsletterRegistry(String source) {
        this.id = UUID.randomUUID();
        this.source = source;
    }

    public static NewsletterRegistry create(String name) {
        return new NewsletterRegistry(name);
    }

    public void update(ZonedDateTime time) {
        this.lastScannedAt = time;
    }

    public UUID getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public Optional<ZonedDateTime> getLastScannedAt() {
        return Optional.ofNullable(lastScannedAt);
    }
}
