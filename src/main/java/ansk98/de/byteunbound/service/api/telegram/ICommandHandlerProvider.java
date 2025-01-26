package ansk98.de.byteunbound.service.api.telegram;

import java.util.Optional;

/**
 * Service provide to resolve the relevant command handler based on the received command.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
public interface ICommandHandlerProvider {

    /**
     * Finds a command handler based on the passed command
     *
     * @param command command
     * @return {@link ICommandHandler}
     */
    ICommandHandler resolve(Optional<String> command);
}
