package ansk98.de.byteunbound.repository;

import ansk98.de.byteunbound.domain.NewsletterRegistry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface INewsletterRegistryRepository extends JpaRepository<NewsletterRegistry, UUID> {

    Optional<NewsletterRegistry> findNewsletterRegistriesBySource(String simpleName);
}
