package ansk98.de.byteunbound.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("client.telegram")
public record TelegramProperties(String botId, String groupId, String token) {
}
