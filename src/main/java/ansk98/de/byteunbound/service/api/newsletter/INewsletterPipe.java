package ansk98.de.byteunbound.service.api.newsletter;

import ansk98.de.byteunbound.service.parameter.newsletter.IAbstractNewsletter;
import ansk98.de.byteunbound.service.parameter.telegram.Newsletter;

/**
 * Pipe that transforms an {@link IAbstractNewsletter} to {@link Newsletter}.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
public interface INewsletterPipe extends IAbstractSupport {

    /**
     * Transforms the abstract newsletter representation to {@link Newsletter} that is ready to be sent out to the group.
     *
     * @param newsletter newsletter
     * @return transformed newsletter
     */
    Newsletter transform(IAbstractNewsletter newsletter);

}
