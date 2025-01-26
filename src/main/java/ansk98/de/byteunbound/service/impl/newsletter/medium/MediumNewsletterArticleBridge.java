package ansk98.de.byteunbound.service.impl.newsletter.medium;

import ansk98.de.byteunbound.properties.MediumNewsletterProperties;
import ansk98.de.byteunbound.service.api.newsletter.INewsletterArticleBridge;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link INewsletterArticleBridge}.
 *
 * @author Anton SKripin (anton.tech98@gmail.com)
 */
@Service
public class MediumNewsletterArticleBridge implements INewsletterArticleBridge {

    private final MediumNewsletterProperties newsletterProperties;

    public MediumNewsletterArticleBridge(MediumNewsletterProperties newsletterProperties) {
        this.newsletterProperties = newsletterProperties;
    }

    @Override
    public String bridge(String articleLink) {
        return String.format("%s/%s", newsletterProperties.redirectToHost(), articleLink);
    }
}
