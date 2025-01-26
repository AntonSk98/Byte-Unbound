package ansk98.de.byteunbound.service.impl.telegram;

import ansk98.de.byteunbound.service.api.telegram.ICommandHandler;
import ansk98.de.byteunbound.service.api.telegram.ICommandHandlerProvider;
import ansk98.de.byteunbound.service.parameter.telegram.Command;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link ICommandHandlerProvider}.
 *
 * @author Anton SKripin (anton.tech98@gmail.com)
 */
@Component
public class CommandHandlerProvider implements ICommandHandlerProvider {

    private final List<ICommandHandler> commandHandlers;

    public CommandHandlerProvider(List<ICommandHandler> commandHandlers) {
        this.commandHandlers = commandHandlers;
    }

    @Override
    public ICommandHandler resolve(Optional<String> commandOpt) {
        Command command = commandOpt.map(Command::find).orElse(Command.UNKNOWN);
        return commandHandlers
                .stream()
                .filter(handler -> handler.supports(command))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No handler found for command: " + command.getCommand()));
    }
}
