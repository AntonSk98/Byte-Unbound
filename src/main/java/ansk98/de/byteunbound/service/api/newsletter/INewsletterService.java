package ansk98.de.byteunbound.service.api.newsletter;

import ansk98.de.byteunbound.service.parameter.newsletter.AbstractNewsletterContainer;

import java.util.List;

public interface INewsletterService {

    List<AbstractNewsletterContainer> findPublishedNewsletters();

    void sendNewsletters(List<AbstractNewsletterContainer> newsletters);
}
