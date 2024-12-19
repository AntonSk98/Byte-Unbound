package ansk98.de.byteunbound.service.impl.telegram;

import ansk98.de.byteunbound.properties.TelegramProperties;
import ansk98.de.byteunbound.service.api.telegram.ITelegramClient;
import ansk98.de.byteunbound.service.parameter.telegram.Newsletter;
import ansk98.de.byteunbound.service.parameter.telegram.Post;
import okhttp3.OkHttpClient;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.client.AbstractTelegramClient;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Implementation of {@link ITelegramClient}.
 *
 * @author Anton SKripin (anton.tech98@gmail.com)
 */
@Service
public class TelegramClient implements ITelegramClient {

    private final TelegramProperties telegramProperties;
    private final AbstractTelegramClient telegramClient;

    public TelegramClient(TelegramProperties telegramProperties) {
        this.telegramProperties = telegramProperties;
        this.telegramClient = new OkHttpTelegramClient(
                new OkHttpClient.Builder()
                        .connectTimeout(5, TimeUnit.MINUTES)
                        .writeTimeout(5, TimeUnit.MINUTES)
                        .readTimeout(5, TimeUnit.MINUTES)
                        .build(),
                telegramProperties.token()
        );
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

    @Override
    public void sendArticle(String title, List<InputStream> binariesStream) {
        sendMessageToBot("Sending an article with " + binariesStream.size() + " images in it.");
        var binaryBatches = ListUtils.partition(binariesStream, 10);
        int pages = binaryBatches.size();
        boolean singlePage = pages == 1;
        AtomicInteger element = new AtomicInteger();
        AtomicInteger page = new AtomicInteger(1);

        binaryBatches
                .forEach(binaryBatch -> {
                    List<InputMedia> mediaGroup = new ArrayList<>();
                    binaryBatch.forEach(binary -> {
                        InputMediaPhoto mediaPhoto = new InputMediaPhoto(binary, "article_" + page.get() + "_" + element.getAndIncrement());
                        if (mediaGroup.isEmpty()) {
                            final String pagePostfix = singlePage ? "" : "\n\n<i>Page " + page.get() + " out of " + pages + "</i>";
                            final String caption = String.format("<b>#Article %s</b> %s", title, pagePostfix);
                            mediaPhoto.setCaption(caption);
                            mediaPhoto.setParseMode("HTML");
                        }
                        mediaGroup.add(mediaPhoto);
                    });

                    SendMediaGroup sendMediaGroup = new SendMediaGroup(telegramProperties.groupId(), mediaGroup);
                    sendMediaGroup(sendMediaGroup);

                    if (!singlePage && page.get() != pages) {
                        final Duration awaitDuration = Duration.ofSeconds(20);
                        sendMessageToBot("Sent page " + page.get() + " out of " + pages + " pages. Waiting for " + awaitDuration.toSeconds() + " seconds...");
                        await(awaitDuration);
                    }

                    page.getAndIncrement();
                });

        sendMessageToBot("Successfully sent the article to the group!");
    }

    private static void await(Duration duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendMediaGroup(SendMediaGroup sendMediaGroup) {
        try {
            telegramClient.execute(sendMediaGroup);
        } catch (TelegramApiException e) {
            throw new RuntimeException("Failed to send media group", e);
        }

    }

    private void sendMessage(SendMessage message) {
        try {
            telegramClient.execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException("Failed to send message", e);
        }
    }

    @Override
    public InputStream streamFile(String fileId) {
        GetFile getFileMethod = new GetFile(fileId);
        try {
            File file = telegramClient.execute(getFileMethod);
            return telegramClient.downloadFileAsStream(file);
        } catch (TelegramApiException e) {
            throw new RuntimeException("Failed to get file", e);
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
