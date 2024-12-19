package ansk98.de.byteunbound.service.impl.telegram.handler;

import ansk98.de.byteunbound.service.api.telegram.ICommandHandler;
import ansk98.de.byteunbound.service.api.telegram.ITelegramClient;
import ansk98.de.byteunbound.service.parameter.telegram.Command;
import ansk98.de.byteunbound.service.parameter.telegram.Parameters;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link ICommandHandler}.
 *
 * @author Anton SKripin (anton.tech98@gmail.com)
 */
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
    public boolean supports(Command command) {
        return Command.UNKNOWN.equals(command);
    }
}
