package ansk98.de.byteunbound.service.api.telegram;

import ansk98.de.byteunbound.service.parameter.telegram.Command;

/**
 * Abstract interface to find the supported {@link ICommandHandler} depending on the received {@link Command}.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
public interface IAbstractCommandSupport {

    /**
     * Checks whether a received command is supported based on the passed {@link Command}.
     *
     * @param command command
     * @return true if supported
     */
    boolean supports(Command command);

}
