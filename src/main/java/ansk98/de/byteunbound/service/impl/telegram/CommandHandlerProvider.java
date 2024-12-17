package ansk98.de.byteunbound.service.impl.telegram;

import ansk98.de.byteunbound.service.api.telegram.ICommandHandler;
import ansk98.de.byteunbound.service.api.telegram.ICommandHandlerProvider;
import ansk98.de.byteunbound.service.parameter.telegram.Commands;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CommandHandlerProvider implements ICommandHandlerProvider {

    private final List<ICommandHandler> commandHandlers;

    public CommandHandlerProvider(List<ICommandHandler> commandHandlers) {
        this.commandHandlers = commandHandlers;
    }

    @Override
    public ICommandHandler resolve(Optional<String> commandOpt) {
        Commands command = commandOpt.map(Commands::find).orElse(Commands.UNKNOWN);
        return commandHandlers
                .stream()
                .filter(handler -> handler.supports(command))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No handler found for command: " + command.getCommand()));
    }
}
