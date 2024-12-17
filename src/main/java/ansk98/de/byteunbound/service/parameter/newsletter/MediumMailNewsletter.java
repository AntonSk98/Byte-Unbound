package ansk98.de.byteunbound.service.parameter.newsletter;

import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

public record MediumMailNewsletter(List<String> newsletterLinks) implements IAbstractNewsletter {

    @Override
    public boolean isEmpty() {
        return emptyIfNull(newsletterLinks).isEmpty();
    }
}
