package ansk98.de.byteunbound.service.impl.telegram;

import ansk98.de.byteunbound.properties.TelegramProperties;
import ansk98.de.byteunbound.service.api.telegram.ITelegramConsumer;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Component that starts up Telegram Bot.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
@Component
public class TelegramInitializer {

    private final TelegramProperties telegramProperties;
    private final ITelegramConsumer telegramConsumer;

    public TelegramInitializer(TelegramProperties telegramProperties,
                               ITelegramConsumer telegramConsumer) {
        this.telegramProperties = telegramProperties;
        this.telegramConsumer = telegramConsumer;
    }

    @PostConstruct
    public void initTelegramBot() throws TelegramApiException {
        TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication();
        botsApplication.registerBot(telegramProperties.token(), telegramConsumer);
    }
}
