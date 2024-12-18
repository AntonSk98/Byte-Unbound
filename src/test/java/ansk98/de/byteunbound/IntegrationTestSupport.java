package ansk98.de.byteunbound;

import ansk98.de.byteunbound.service.api.email.IEmailReader;
import ansk98.de.byteunbound.service.api.telegram.ITelegramClient;
import ansk98.de.byteunbound.service.impl.telegram.TelegramInitializer;
import org.springframework.test.context.bean.override.mockito.MockitoBean;


public class IntegrationTestSupport {

    @MockitoBean
    private ITelegramClient telegramClient;

    @MockitoBean
    private IEmailReader emailReader;

    @MockitoBean
    private TelegramInitializer telegramInitializer;
}
