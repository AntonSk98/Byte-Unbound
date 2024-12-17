package ansk98.de.byteunbound.service.api.newsletter;

import ansk98.de.byteunbound.domain.NewsletterRegistry;

public interface INewsletterRegistryService {

    NewsletterRegistry getOrCreateRegistry(Class<?> source);
}
