package ansk98.de.byteunbound.service.api.telegram;

import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;

/**
 * Entry point service to all received messages via Telegram.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
public interface ITelegramConsumer extends LongPollingSingleThreadUpdateConsumer {
}
