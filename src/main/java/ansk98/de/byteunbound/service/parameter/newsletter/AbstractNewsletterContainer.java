package ansk98.de.byteunbound.service.parameter.newsletter;

import java.time.ZonedDateTime;

/**
 * Abstract container containing newsletter and its metadata information.
 *
 * @param newsletterMetadata newsletter metadata
 * @param newsletter         newsletter
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
public record AbstractNewsletterContainer(NewsletterMetadata newsletterMetadata, IAbstractNewsletter newsletter) {

    public record NewsletterMetadata(Class<?> source, ZonedDateTime searchDateTime) {

    }
}
