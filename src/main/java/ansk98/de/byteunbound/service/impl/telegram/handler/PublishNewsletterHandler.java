package ansk98.de.byteunbound.service.impl.telegram.handler;

import ansk98.de.byteunbound.service.api.newsletter.ISelfNewsletterService;
import ansk98.de.byteunbound.service.api.telegram.ICommandHandler;
import ansk98.de.byteunbound.service.api.telegram.ITelegramClient;
import ansk98.de.byteunbound.service.parameter.telegram.Command;
import ansk98.de.byteunbound.service.parameter.telegram.Parameters;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link ICommandHandler}.
 *
 * @author Anton SKripin (anton.tech98@gmail.com)
 */
@Component
public class PublishNewsletterHandler implements ICommandHandler {

    private final ITelegramClient telegramClient;
    private final ISelfNewsletterService newsletterService;

    public PublishNewsletterHandler(ITelegramClient telegramClient,
                                    ISelfNewsletterService newsletterService) {
        this.telegramClient = telegramClient;
        this.newsletterService = newsletterService;
    }

    @Override
    public void apply(Parameters parameters) {
        if (CollectionUtils.isEmpty(newsletterService.getNewsletter())) {
            telegramClient.sendMessageToBot("There is no newsletter to be published...");
            return;
        }
        newsletterService.markAsPublishedNewsletter();
        telegramClient.sendMessageToBot("Newsletter is marked as published and soon it is gonna be posted!");
    }

    @Override
    public boolean supports(Command command) {
        return Command.PUBLISH_NEWSLETTER.equals(command);
    }
}
