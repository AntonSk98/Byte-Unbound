package ansk98.de.byteunbound.service.impl.newsletter.self;

import ansk98.de.byteunbound.service.api.newsletter.INewsletterPipe;
import ansk98.de.byteunbound.service.parameter.newsletter.IAbstractNewsletter;
import ansk98.de.byteunbound.service.parameter.newsletter.SelfTelegramNewsletter;
import ansk98.de.byteunbound.service.parameter.telegram.Newsletter;
import ansk98.de.byteunbound.service.parameter.telegram.Post;
import org.springframework.stereotype.Component;

@Component
public class SelfNewsletterPipe implements INewsletterPipe {

    @Override
    public Newsletter transform(IAbstractNewsletter newsletter) {
        SelfTelegramNewsletter publishedNewsletter = newsletter.map(SelfTelegramNewsletter.class);
        return new Newsletter(publishedNewsletter.articles()
                .stream()
                .map(article -> new Post(article.title(), article.link()))
                .toList()
        );
    }

    @Override
    public boolean supports(Class<?> source) {
        return SelfNewsletterConsumer.class.equals(source);
    }
}
