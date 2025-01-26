package ansk98.de.byteunbound.service.parameter.newsletter;

/**
 * Common newsletter signature that is common for all newsletters.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
public interface IAbstractNewsletter {

    /**
     * If current newsletter contains no articles.
     *
     * @return true if the newsletter contains no relevant articles
     */
    boolean isEmpty();

    /**
     * Maps an {@link IAbstractNewsletter} to its concrete newsletter implementation
     *
     * @param clazz        class to cast to
     * @param <Newsletter> type of the newsletter
     * @return mapped newsletter object
     */
    default <Newsletter> Newsletter map(Class<Newsletter> clazz) {
        return clazz.cast(this);
    }
}
