package ansk98.de.byteunbound.service.impl.newsletter.medium;

import ansk98.de.byteunbound.service.api.newsletter.INewsletterPipe;
import ansk98.de.byteunbound.service.parameter.newsletter.IAbstractNewsletter;
import ansk98.de.byteunbound.service.parameter.newsletter.MediumMailNewsletter;
import ansk98.de.byteunbound.service.parameter.telegram.Newsletter;
import ansk98.de.byteunbound.service.parameter.telegram.Post;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.SocketException;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link INewsletterPipe}.
 *
 * @author Anton SKripin (anton.tech98@gmail.com)
 */
@Component
public class MediumNewsletterPipe implements INewsletterPipe {

    private static final Logger LOGGER = LoggerFactory.getLogger(MediumNewsletterPipe.class);

    @Override
    public Newsletter transform(IAbstractNewsletter newsletter) {
        MediumMailNewsletter mediumMailNewsletter = newsletter.map(MediumMailNewsletter.class);

        List<Post> posts = mediumMailNewsletter.articleLinks()
                .stream()
                .map(this::toPost)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        return new Newsletter(posts);
    }

    @Override
    public boolean supports(Class<?> source) {
        return MediumNewsletterConsumer.class.equals(source);
    }

    private Optional<Post> toPost(String articleLink) {
        return document(articleLink)
                .map(document -> document.selectFirst("h1"))
                .map(Element::text)
                .map(title -> new Post(title, articleLink));

    }

    private static Optional<Document> document(String link) {
        try {
            return Optional.of(Jsoup.connect(link).get());
        } catch (SocketException e) {
            LOGGER.error("Network error. Error: {}", e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            LOGGER.error("Could not get document from link {}. Error: {}", link, e.getMessage());
            return Optional.empty();
        }
    }
}
