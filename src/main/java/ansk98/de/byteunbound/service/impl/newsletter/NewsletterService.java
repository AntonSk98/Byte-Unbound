package ansk98.de.byteunbound.service.impl.newsletter;

import ansk98.de.byteunbound.domain.NewsletterRegistry;
import ansk98.de.byteunbound.service.api.newsletter.*;
import ansk98.de.byteunbound.service.api.telegram.ITelegramClient;
import ansk98.de.byteunbound.service.parameter.newsletter.AbstractNewsletterContainer;
import ansk98.de.byteunbound.service.parameter.newsletter.IAbstractNewsletter;
import ansk98.de.byteunbound.service.parameter.telegram.Newsletter;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsletterService implements INewsletterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewsletterService.class);

    private final INewsletterRegistryService newsletterRegistryService;
    private final List<INewsletterConsumer> newsletterConsumers;

    private final INewsletterPipeProvider newsletterPipeProvider;
    private final ITelegramClient telegramClient;

    public NewsletterService(INewsletterRegistryService newsletterRegistryService,
                             List<INewsletterConsumer> newsletterConsumers,
                             INewsletterPipeProvider newsletterPipeProvider,
                             ITelegramClient telegramClient) {
        this.newsletterRegistryService = newsletterRegistryService;
        this.newsletterConsumers = newsletterConsumers;
        this.newsletterPipeProvider = newsletterPipeProvider;
        this.telegramClient = telegramClient;
    }

    @Override
    @Transactional
    public List<AbstractNewsletterContainer> findPublishedNewsletters() {
        List<AbstractNewsletterContainer> abstractNewsletters = new ArrayList<>();

        for (INewsletterConsumer newsletterConsumer : newsletterConsumers) {
            ZonedDateTime searchDateTime = ZonedDateTime.now();
            NewsletterRegistry newsletterRegistry = newsletterRegistryService.getOrCreateRegistry(newsletterConsumer.getSource());

            IAbstractNewsletter abstractNewsletter = newsletterConsumer
                    .consumeSince(newsletterRegistry
                            .getLastScannedAt()
                            .orElse(ZonedDateTime.from(Instant.EPOCH.atZone(ZoneId.systemDefault())))
                    );

            if (abstractNewsletter.isEmpty()) {
                continue;
            }

            abstractNewsletters.add(
                    new AbstractNewsletterContainer(
                            new AbstractNewsletterContainer.NewsletterMetadata(newsletterConsumer.getSource(), searchDateTime),
                            abstractNewsletter
                    )
            );
        }

        return abstractNewsletters;
    }

    @Override
    @Transactional
    public void sendNewsletters(List<AbstractNewsletterContainer> newsletters) {
        boolean noNewNewsletters = CollectionUtils.isEmpty(newsletters);

        if (noNewNewsletters) {
            return;
        }

        LOGGER.info("Found {} new newsletters", newsletters.size());

        for (AbstractNewsletterContainer newsletter : newsletters) {
            LOGGER.info("Processing newsletter from source {}", newsletter.newsletterMetadata().source().getSimpleName());

            NewsletterRegistry newsletterRegistry = newsletterRegistryService.getOrCreateRegistry(newsletter.newsletterMetadata().source());
            newsletterRegistry.update(newsletter.newsletterMetadata().searchDateTime());

            INewsletterPipe newsletterPipe = newsletterPipeProvider.newsletterPipe(newsletter.newsletterMetadata().source());
            Newsletter toBeSentNewsletter = newsletterPipe.transform(newsletter.newsletter());
            telegramClient.sendNewsletter(toBeSentNewsletter);

            LOGGER.info("Successfully sent a newsletter!");
        }
    }
}
