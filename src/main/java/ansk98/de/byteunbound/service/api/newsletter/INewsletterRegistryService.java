package ansk98.de.byteunbound.service.api.newsletter;

import ansk98.de.byteunbound.domain.NewsletterRegistry;

/**
 * Service to manage {@link NewsletterRegistry} to track the state of consumed newsletters.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
public interface INewsletterRegistryService {

    /**
     * Finds a {@link NewsletterRegistry} by the source.
     * In case, the source was recently added creates a new {@link NewsletterRegistry}.
     *
     * @param source source
     * @return {@link NewsletterRegistry}
     */
    NewsletterRegistry getOrCreateRegistry(Class<?> source);
}
