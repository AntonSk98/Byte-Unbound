package ansk98.de.byteunbound.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for Telegram Bot.
 *
 * @param botId   Bot ID.
 * @param groupId ID of the group where content is posted.
 * @param token   Bot token.
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
@ConfigurationProperties("client.telegram")
public record TelegramProperties(String botId, String groupId, String token) {
}
