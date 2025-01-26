package ansk98.de.byteunbound.service.api.newsletter;

import ansk98.de.byteunbound.service.parameter.newsletter.IAbstractNewsletter;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Abstract newsletter consumer that finds articles after the specified time.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
public interface INewsletterConsumer {

    /**
     * Find a newsletter that was published after the given timestamp.
     *
     * @param dateTime timestamp
     * @return {@link IAbstractNewsletter}
     */
    List<? extends IAbstractNewsletter> consumeSince(ZonedDateTime dateTime);

    /**
     * Returns the source of the newsletter consumer
     *
     * @return source
     */
    Class<?> getSource();
}
