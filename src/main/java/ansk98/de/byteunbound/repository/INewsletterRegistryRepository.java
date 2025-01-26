package ansk98.de.byteunbound.repository;

import ansk98.de.byteunbound.domain.NewsletterRegistry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository to manage {@link NewsletterRegistry}.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
public interface INewsletterRegistryRepository extends JpaRepository<NewsletterRegistry, UUID> {

    /**
     * Finds a {@link NewsletterRegistry} based on the passed source.
     *
     * @param source source
     * @return state of the newsletter consumer
     */
    Optional<NewsletterRegistry> findNewsletterRegistriesBySource(String source);
}
