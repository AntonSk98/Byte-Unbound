package ansk98.de.byteunbound.service.api.newsletter;

import ansk98.de.byteunbound.service.parameter.newsletter.AbstractNewsletterContainer;

import java.util.List;

/**
 * Service to manage the state of the tracked newsletter sources.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
public interface INewsletterService {

    /**
     * Finds all newly published newsletters.
     *
     * @return newsletters
     */
    List<AbstractNewsletterContainer> findPublishedNewsletters();

    /**
     * Sends all newly published newsletters.
     *
     * @param newsletters newsletters
     */
    void sendNewsletters(List<AbstractNewsletterContainer> newsletters);
}
