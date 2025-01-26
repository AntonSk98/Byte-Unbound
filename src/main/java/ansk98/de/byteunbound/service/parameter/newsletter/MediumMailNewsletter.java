package ansk98.de.byteunbound.service.parameter.newsletter;

import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

/**
 * Newsletter fetched from the Medium source.
 *
 * @param articleLinks article links of the newsletter
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
public record MediumMailNewsletter(List<String> articleLinks) implements IAbstractNewsletter {

    @Override
    public boolean isEmpty() {
        return emptyIfNull(articleLinks).isEmpty();
    }
}
