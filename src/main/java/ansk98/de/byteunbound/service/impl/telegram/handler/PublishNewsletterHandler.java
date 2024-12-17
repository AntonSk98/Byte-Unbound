package ansk98.de.byteunbound.service.impl.telegram.handler;

import ansk98.de.byteunbound.service.api.newsletter.ISelfNewsletterService;
import ansk98.de.byteunbound.service.api.telegram.ICommandHandler;
import ansk98.de.byteunbound.service.api.telegram.ITelegramClient;
import ansk98.de.byteunbound.service.parameter.telegram.Commands;
import ansk98.de.byteunbound.service.parameter.telegram.Parameters;
import org.springframework.stereotype.Component;

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
        newsletterService.markAsPublishedNewsletter();
        telegramClient.sendMessageToBot("Newsletter is marked as published and soon it is gonna be posted!");
    }

    @Override
    public boolean supports(Commands command) {
        return Commands.PUBLISH_NEWSLETTER.equals(command);
    }
}
