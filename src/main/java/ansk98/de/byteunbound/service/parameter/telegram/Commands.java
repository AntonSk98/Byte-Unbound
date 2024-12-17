package ansk98.de.byteunbound.service.parameter.telegram;

import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;

public enum Commands {

    NEW_ARTICLE("/new-article"),
    NON_PUBLISHED_NEWSLETTER("/non-published-newsletter"),
    PUBLISH_NEWSLETTER("/publish-newsletter"),
    UNKNOWN(Strings.EMPTY);

    private final String command;

    Commands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static Commands find(String maybeCommand) {
        return Arrays.stream(Commands.values())
                .filter(command -> maybeCommand.startsWith(command.getCommand()))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
