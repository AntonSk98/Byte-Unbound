package ansk98.de.byteunbound.service.parameter.newsletter;

import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

public record SelfTelegramNewsletter(List<SelfArticle> articles) implements IAbstractNewsletter {

    @Override
    public boolean isEmpty() {
        return emptyIfNull(articles).isEmpty();
    }

    public record SelfArticle(String title, String link) {
    }
}
