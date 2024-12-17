package ansk98.de.byteunbound.service.api.telegram;

import ansk98.de.byteunbound.service.parameter.telegram.Commands;

public interface IAbstractCommandSupport {
    boolean supports(Commands command);

}
