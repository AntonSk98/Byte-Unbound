package ansk98.de.byteunbound.service.parameter.email;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Record that contains configuration properties required to connect to SMTP Server.
 *
 * @param host     host
 * @param username username
 * @param password password
 * @param port     port
 * @param ssl      connection via ssl
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
@ConfigurationProperties("email")
public record EmailProperties(String host, String username, String password, String port, String ssl) {
}
