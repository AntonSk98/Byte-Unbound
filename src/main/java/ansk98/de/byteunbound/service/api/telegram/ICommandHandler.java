package ansk98.de.byteunbound.service.api.telegram;

import ansk98.de.byteunbound.service.parameter.telegram.Parameters;

/**
 * Service to process the received command.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
public interface ICommandHandler extends IAbstractCommandSupport {

    /**
     * Handles the Telegram command.
     *
     * @param parameters parameters
     */
    void apply(Parameters parameters);
}
