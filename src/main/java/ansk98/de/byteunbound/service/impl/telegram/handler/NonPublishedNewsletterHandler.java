package ansk98.de.byteunbound.service.impl.telegram.handler;

import ansk98.de.byteunbound.domain.Article;
import ansk98.de.byteunbound.service.api.newsletter.ISelfNewsletterService;
import ansk98.de.byteunbound.service.api.telegram.ICommandHandler;
import ansk98.de.byteunbound.service.api.telegram.ITelegramClient;
import ansk98.de.byteunbound.service.parameter.telegram.Command;
import ansk98.de.byteunbound.service.parameter.telegram.Parameters;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of {@link ICommandHandler}.
 *
 * @author Anton SKripin (anton.tech98@gmail.com)
 */
@Component
public class NonPublishedNewsletterHandler implements ICommandHandler {

    private final ISelfNewsletterService newsletterService;
    private final ITelegramClient telegramClient;

    public NonPublishedNewsletterHandler(ISelfNewsletterService newsletterService,
                                         ITelegramClient telegramClient) {
        this.newsletterService = newsletterService;
        this.telegramClient = telegramClient;
    }

    @Override
    public void apply(Parameters parameters) {
        List<Article> nonPublishedArticles = newsletterService.getNewsletter();

        String message = new StringBuilder("<b>Non-published newsletter: </b>")
                .append("\n\n")
                .append(
                        nonPublishedArticles
                                .stream()
                                .map(article -> String.format("%s -> %s", article.getTitle(), article.getLink()))
                                .collect(Collectors.joining("\n"))
                )
                .toString();

        telegramClient.sendMessageToBot(message);
    }

    @Override
    public boolean supports(Command command) {
        return Command.NON_PUBLISHED_NEWSLETTER.equals(command);
    }
}
