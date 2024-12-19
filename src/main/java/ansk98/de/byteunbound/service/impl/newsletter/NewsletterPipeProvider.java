package ansk98.de.byteunbound.service.impl.newsletter;

import ansk98.de.byteunbound.service.api.newsletter.INewsletterPipe;
import ansk98.de.byteunbound.service.api.newsletter.INewsletterPipeProvider;
import ansk98.de.byteunbound.service.api.newsletter.ISelfNewsletterService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementation of {@link INewsletterPipeProvider}.
 *
 * @author Anton SKripin (anton.tech98@gmail.com)
 */
@Component
public class NewsletterPipeProvider implements INewsletterPipeProvider {

    private final List<INewsletterPipe> newsletterPipes;

    public NewsletterPipeProvider(List<INewsletterPipe> newsletterPipes) {
        this.newsletterPipes = newsletterPipes;
    }

    @Override
    public INewsletterPipe newsletterPipe(Class<?> source) {
        return newsletterPipes.stream()
                .filter(pipe -> pipe.supports(source))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Register newsletter pipe for source " + source.getName()));
    }
}
