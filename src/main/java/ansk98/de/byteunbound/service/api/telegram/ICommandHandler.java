package ansk98.de.byteunbound.service.api.telegram;

import ansk98.de.byteunbound.service.parameter.telegram.Parameters;

public interface ICommandHandler extends IAbstractCommandSupport {
    void apply(Parameters parameters);
}
