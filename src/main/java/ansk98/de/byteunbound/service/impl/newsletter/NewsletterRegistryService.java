package ansk98.de.byteunbound.service.impl.newsletter;

import ansk98.de.byteunbound.domain.NewsletterRegistry;
import ansk98.de.byteunbound.repository.INewsletterRegistryRepository;
import ansk98.de.byteunbound.service.api.newsletter.INewsletterPipeProvider;
import ansk98.de.byteunbound.service.api.newsletter.INewsletterRegistryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link INewsletterRegistryService}.
 *
 * @author Anton SKripin (anton.tech98@gmail.com)
 */
@Service
public class NewsletterRegistryService implements INewsletterRegistryService {

    private final INewsletterRegistryRepository newsletterRegistryRepository;

    public NewsletterRegistryService(INewsletterRegistryRepository newsletterRegistryRepository) {
        this.newsletterRegistryRepository = newsletterRegistryRepository;
    }

    @Override
    @Transactional
    public NewsletterRegistry getOrCreateRegistry(Class<?> source) {
        final String sourceId = source.getSimpleName();

        return newsletterRegistryRepository
                .findNewsletterRegistriesBySource(sourceId)
                .orElseGet(() -> {
                    NewsletterRegistry newsletterRegistry = NewsletterRegistry.create(sourceId);
                    return newsletterRegistryRepository.save(newsletterRegistry);
                });
    }
}
