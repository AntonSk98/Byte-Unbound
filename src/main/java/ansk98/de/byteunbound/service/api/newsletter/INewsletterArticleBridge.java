package ansk98.de.byteunbound.service.api.newsletter;

/**
 * Service that modifies an article link to redirect to its free-to-read alternative.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
public interface INewsletterArticleBridge {

    /**
     * Maps a link to its free-to-read link alternative.
     *
     * @param articleLink article link
     * @return article link
     */
    String bridge(String articleLink);
}
