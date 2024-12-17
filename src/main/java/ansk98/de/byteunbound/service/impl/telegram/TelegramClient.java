package ansk98.de.byteunbound.service.impl.telegram;

import ansk98.de.byteunbound.properties.TelegramProperties;
import ansk98.de.byteunbound.service.api.telegram.ITelegramClient;
import ansk98.de.byteunbound.service.parameter.telegram.Newsletter;
import ansk98.de.byteunbound.service.parameter.telegram.Post;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.client.AbstractTelegramClient;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class TelegramClient implements ITelegramClient {

    private final TelegramProperties telegramProperties;
    private final AbstractTelegramClient telegramClient;

    public TelegramClient(TelegramProperties telegramProperties) {
        this.telegramProperties = telegramProperties;
        this.telegramClient = new OkHttpTelegramClient(telegramProperties.token());
    }

    @Override
    public void sendNewsletter(Newsletter newsletter) {
        String content = buildNewsletterMessage(newsletter);

        SendMessage message = new SendMessage(telegramProperties.groupId(), content);
        message.setParseMode("HTML");
        message.disableWebPagePreview();
        sendMessage(message);
        sendMessageToBot(String.format("Sent a newsletter with %s articles in it.", newsletter.posts().size()));
    }

    @Override
    public void sendMessageToBot(String message) {
        SendMessage sendMessage = new SendMessage(telegramProperties.botId(), message);
        sendMessage.setParseMode("HTML");
        sendMessage(sendMessage);
    }

    private void sendMessage(SendMessage message) {
        try {
            telegramClient.execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException("Failed to send message", e);
        }
    }

    private String buildNewsletterMessage(Newsletter newsletter) {
        StringBuilder messageBuilder = new StringBuilder();

        // Header for the newsletter
        messageBuilder.append("🔥 <b>Your Tech Digest</b> 🔥\n\n");

        // Format each post
        for (Post post : newsletter.posts()) {
            messageBuilder.append(formatPost(post)).append("\n");
        }

        // Footer or a call to action
        messageBuilder.append("\n\uD83D\uDE80 Stay updated with the latest in tech!\n");

        return messageBuilder.toString();
    }

    private String formatPost(Post post) {
        String link = post.link() != null ? post.link() : "#"; // Fallback for missing links
        return new StringBuilder()
                .append("\uD83D\uDD39 ")
                .append("<b><a href=\"")
                .append(link)
                .append("\">")
                .append(post.title())
                .append("</a></b>")
                .append("\n")
                .append("• • •")
                .toString();
    }
}
