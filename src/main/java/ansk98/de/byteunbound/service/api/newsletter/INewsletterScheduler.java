package ansk98.de.byteunbound.service.api.newsletter;

/**
 * Scheduler service that regularly checks if in all {@link INewsletterConsumer} there are new articles available.
 * If this is the case, the scheduler publishes the new articles.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
public interface INewsletterScheduler {

    /**
     * Job that finds newly published newsletters and send them.
     */
    void sendPublishedNewsletters();
}
