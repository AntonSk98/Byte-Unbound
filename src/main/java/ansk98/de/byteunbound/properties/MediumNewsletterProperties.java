package ansk98.de.byteunbound.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("newsletter.medium")
public record MediumNewsletterProperties(String sender,
                                         String articlePattern,
                                         String substringBefore,
                                         String redirectToHost) {
}
