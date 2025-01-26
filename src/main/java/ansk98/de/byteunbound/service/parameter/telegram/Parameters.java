package ansk98.de.byteunbound.service.parameter.telegram;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;

/**
 * Parameters of a received {@link Command}.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
public class Parameters {
    private static final String paramStart = "(";
    private static final String paramEnd = ")";
    private static final String paramSeparator = ",";
    private final Iterator<String> parametersIterator;

    public Parameters(Optional<String> command) {
        if (command.isEmpty()) {
            this.parametersIterator = Collections.emptyIterator();
            return;
        }

        String parametersString = StringUtils.substringBetween(command.get(), paramStart, paramEnd);

        if (StringUtils.isEmpty(parametersString)) {
            this.parametersIterator = Collections.emptyIterator();
            return;
        }

        this.parametersIterator = Arrays
                .stream(parametersString
                        .split(paramSeparator))
                .map(StringUtils::trim)
                .toList()
                .iterator();
    }

    /**
     * Returns the next parameter or empty optional.
     *
     * @return optional of the next parameter
     */
    public Optional<String> getNextParameter() {
        if (parametersIterator.hasNext()) {
            return Optional.ofNullable(parametersIterator.next());
        }
        return Optional.empty();
    }
}
