package ansk98.de.byteunbound.service.parameter.telegram;

import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;

/**
 * Enum that contains all ready-to-be-handled commands.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
public enum Command {

    NEW_ARTICLE("/new_article"),
    NON_PUBLISHED_NEWSLETTER("/non_published_newsletter"),
    PUBLISH_NEWSLETTER("/publish_newsletter"),
    UNKNOWN(Strings.EMPTY);

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    /**
     * Resolves a {@link Command} from the client's message
     *
     * @param maybeCommand client text that might be a command or may result in {@link #UNKNOWN} command
     * @return command
     */
    public static Command find(String maybeCommand) {
        return Arrays.stream(Command.values())
                .filter(command -> maybeCommand.startsWith(command.getCommand()))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
