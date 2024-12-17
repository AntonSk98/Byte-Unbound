package ansk98.de.byteunbound.service.api.newsletter;

import ansk98.de.byteunbound.service.parameter.newsletter.IAbstractNewsletter;
import ansk98.de.byteunbound.service.parameter.telegram.Newsletter;

public interface INewsletterPipe extends IAbstractSupport {

    Newsletter transform(IAbstractNewsletter newsletter);

}
