package ansk98.de.byteunbound.service.impl.telegram;

import ansk98.de.byteunbound.exception.GlobalExceptionWrapper;
import ansk98.de.byteunbound.properties.TelegramProperties;
import ansk98.de.byteunbound.service.api.telegram.IAttachmentHandler;
import ansk98.de.byteunbound.service.api.telegram.ICommandHandler;
import ansk98.de.byteunbound.service.api.telegram.ICommandHandlerProvider;
import ansk98.de.byteunbound.service.api.telegram.ITelegramConsumer;
import ansk98.de.byteunbound.service.parameter.telegram.Parameters;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.chat.Chat;
import org.telegram.telegrambots.meta.api.objects.message.Message;

import java.util.List;
import java.util.Optional;

@Service
public class TelegramConsumer implements ITelegramConsumer {

    private final ICommandHandlerProvider handlerProvider;
    private final ApplicationEventPublisher eventPublisher;
    private final TelegramProperties telegramProperties;
    private final List<IAttachmentHandler> attachmentHandlers;

    public TelegramConsumer(ICommandHandlerProvider handlerProvider,
                            ApplicationEventPublisher eventPublisher,
                            TelegramProperties telegramProperties,
                            List<IAttachmentHandler> attachmentHandlers) {
        this.handlerProvider = handlerProvider;
        this.eventPublisher = eventPublisher;
        this.telegramProperties = telegramProperties;
        this.attachmentHandlers = attachmentHandlers;
    }

    @Override
    public void consume(Update update) {
        GlobalExceptionWrapper.wrap(eventPublisher, () -> {
            validateCanProcessMessage(update);

            Optional<Document> document = Optional
                    .ofNullable(update.getMessage())
                    .map(Message::getDocument);

            if (document.isPresent()) {
                attachmentHandlers.stream()
                        .filter(handler -> handler.supportedMimeType(document.get().getMimeType()))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("No attachment handler found for mime type " + document.get().getMimeType()))
                        .handle(document.get());
                return;
            }

            Optional<String> command = Optional
                    .ofNullable(update.getMessage())
                    .map(Message::getText);

            ICommandHandler commandHandler = handlerProvider.resolve(command);
            commandHandler.apply(new Parameters(command));
        });
    }

    private void validateCanProcessMessage(Update update) {
        String username = Optional.ofNullable(update.getMessage())
                .map(Message::getChat)
                .map(Chat::getUserName)
                .orElse("<unknown>");

        Optional.ofNullable(update.getMessage())
                .map(Message::getChat)
                .map(Chat::getId)
                .filter(chatId -> String.valueOf(chatId).equals(telegramProperties.botId()))
                .orElseThrow(() -> new IllegalStateException("Unauthorized access by user " + username));
    }
}
