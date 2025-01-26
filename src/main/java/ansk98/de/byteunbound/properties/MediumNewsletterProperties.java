package ansk98.de.byteunbound.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration for handling Medium newsletters.
 *
 * @param sender          Sender email.
 * @param articlePattern  Regex for extracting articles.
 * @param substringBefore Keyword before which the article link is truncated.
 * @param redirectToHost  Host to which the link is redirected.
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
@ConfigurationProperties("newsletter.medium")
public record MediumNewsletterProperties(String sender,
                                         String articlePattern,
                                         String substringBefore,
                                         String redirectToHost) {
}
