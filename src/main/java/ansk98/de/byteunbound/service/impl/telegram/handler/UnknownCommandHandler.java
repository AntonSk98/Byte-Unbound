package ansk98.de.byteunbound.service.impl.telegram.handler;

import ansk98.de.byteunbound.service.api.telegram.ICommandHandler;
import ansk98.de.byteunbound.service.api.telegram.ITelegramClient;
import ansk98.de.byteunbound.service.parameter.telegram.Commands;
import ansk98.de.byteunbound.service.parameter.telegram.Parameters;
import org.springframework.stereotype.Component;

@Component
public class UnknownCommandHandler implements ICommandHandler {

    private final ITelegramClient telegramClient;

    public UnknownCommandHandler(ITelegramClient telegramClient) {
        this.telegramClient = telegramClient;
    }

    @Override
    public void apply(Parameters parameters) {
        telegramClient.sendMessageToBot("Unknown command...");
    }

    @Override
    public boolean supports(Commands command) {
        return Commands.UNKNOWN.equals(command);
    }
}
