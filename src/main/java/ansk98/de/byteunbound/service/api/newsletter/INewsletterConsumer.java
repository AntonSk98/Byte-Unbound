package ansk98.de.byteunbound.service.api.newsletter;

import ansk98.de.byteunbound.service.parameter.newsletter.IAbstractNewsletter;

import java.time.ZonedDateTime;

public interface INewsletterConsumer {

    IAbstractNewsletter consumeSince(ZonedDateTime dateTime);

    Class<?> getSource();
}
