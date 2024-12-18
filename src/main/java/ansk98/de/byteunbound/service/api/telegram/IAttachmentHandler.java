package ansk98.de.byteunbound.service.api.telegram;

import org.telegram.telegrambots.meta.api.objects.Document;

public interface IAttachmentHandler {

    void handle(Document document);

    boolean supportedMimeType(String mimeType);
}
