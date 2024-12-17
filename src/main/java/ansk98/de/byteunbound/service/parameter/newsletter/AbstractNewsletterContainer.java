package ansk98.de.byteunbound.service.parameter.newsletter;

import java.time.ZonedDateTime;

public record AbstractNewsletterContainer(NewsletterMetadata newsletterMetadata, IAbstractNewsletter newsletter) {

    public record NewsletterMetadata(Class<?> source, ZonedDateTime searchDateTime) {

    }
}
