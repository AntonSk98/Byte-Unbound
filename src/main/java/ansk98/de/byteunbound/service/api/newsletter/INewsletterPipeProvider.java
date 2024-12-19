package ansk98.de.byteunbound.service.api.newsletter;

/**
 * Provider that finds the relevant {@link INewsletterPipe} depending on the source of the newsletter.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
public interface INewsletterPipeProvider {

    /**
     * Finds the {@link INewsletterPipe} based on the source.
     *
     * @param source source
     * @return {@link INewsletterPipe}
     */
    INewsletterPipe newsletterPipe(Class<?> source);
}
