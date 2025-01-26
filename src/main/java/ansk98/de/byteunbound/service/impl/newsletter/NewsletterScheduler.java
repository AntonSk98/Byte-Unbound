package ansk98.de.byteunbound.service.impl.newsletter;

import ansk98.de.byteunbound.exception.GlobalExceptionWrapper;
import ansk98.de.byteunbound.service.api.newsletter.INewsletterScheduler;
import ansk98.de.byteunbound.service.api.newsletter.INewsletterService;
import ansk98.de.byteunbound.service.parameter.newsletter.AbstractNewsletterContainer;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of {@link INewsletterScheduler}.
 *
 * @author Anton SKripin (anton.tech98@gmail.com)
 */
@Service
public class NewsletterScheduler implements INewsletterScheduler {

    private final INewsletterService newsletterService;
    private final ApplicationEventPublisher eventPublisher;

    public NewsletterScheduler(INewsletterService newsletterService,
                               ApplicationEventPublisher eventPublisher) {
        this.newsletterService = newsletterService;
        this.eventPublisher = eventPublisher;
    }

    @Override
    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.MINUTES)
    public void sendPublishedNewsletters() {
        GlobalExceptionWrapper.wrap(eventPublisher, () -> {
            List<AbstractNewsletterContainer> newsletters = newsletterService.findPublishedNewsletters();
            newsletterService.sendNewsletters(newsletters);
        });
    }
}
