package ansk98.de.byteunbound.service.impl.telegram.handler;

import ansk98.de.byteunbound.domain.Article;
import ansk98.de.byteunbound.service.api.newsletter.ISelfNewsletterService;
import ansk98.de.byteunbound.service.api.telegram.ICommandHandler;
import ansk98.de.byteunbound.service.api.telegram.ITelegramClient;
import ansk98.de.byteunbound.service.parameter.telegram.Commands;
import ansk98.de.byteunbound.service.parameter.telegram.Parameters;
import org.springframework.stereotype.Component;

@Component
public class AddArticleHandler implements ICommandHandler {

    private final ISelfNewsletterService newsletterService;
    private final ITelegramClient telegramClient;

    public AddArticleHandler(ISelfNewsletterService newsletterService,
                             ITelegramClient telegramClient) {
        this.newsletterService = newsletterService;
        this.telegramClient = telegramClient;
    }

    @Override
    public void apply(Parameters parameters) {
        String title = parameters.getNextParameter().orElseThrow(() -> new IllegalStateException("'Title' is a required parameter!"));
        String link = parameters.getNextParameter().orElseThrow(() -> new IllegalStateException("'Link' is a required parameter!"));

        newsletterService.addArticle(new Article.Builder().title(title).link(link));

        telegramClient.sendMessageToBot("Successfully added article!");
    }

    @Override
    public boolean supports(Commands command) {
        return Commands.NEW_ARTICLE.equals(command);
    }
}
