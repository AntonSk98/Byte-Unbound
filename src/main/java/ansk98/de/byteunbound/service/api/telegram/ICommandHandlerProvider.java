package ansk98.de.byteunbound.service.api.telegram;

import java.util.Optional;

public interface ICommandHandlerProvider {

    ICommandHandler resolve(Optional<String> command);
}
