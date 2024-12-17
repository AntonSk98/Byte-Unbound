package ansk98.de.byteunbound.service.impl.telegram;

import ansk98.de.byteunbound.exception.GlobalExceptionWrapper;
import ansk98.de.byteunbound.service.api.telegram.ICommandHandler;
import ansk98.de.byteunbound.service.api.telegram.ICommandHandlerProvider;
import ansk98.de.byteunbound.service.api.telegram.ITelegramConsumer;
import ansk98.de.byteunbound.service.parameter.telegram.Parameters;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;

import java.util.Optional;

@Service
public class TelegramConsumer implements ITelegramConsumer {

    private final ICommandHandlerProvider handlerProvider;
    private final ApplicationEventPublisher eventPublisher;

    public TelegramConsumer(ICommandHandlerProvider handlerProvider,
                            ApplicationEventPublisher eventPublisher) {
        this.handlerProvider = handlerProvider;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void consume(Update update) {
        GlobalExceptionWrapper.wrap(eventPublisher, () -> {
            Optional<String> command = Optional
                    .ofNullable(update.getMessage())
                    .map(Message::getText);

            ICommandHandler commandHandler = handlerProvider.resolve(command);
            commandHandler.apply(new Parameters(command));
        });
    }
}
