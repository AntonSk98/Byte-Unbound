package ansk98.de.byteunbound.service.parameter.email;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("email")
public record EmailProperties(String host, String username, String password, String port, String ssl) {
}
