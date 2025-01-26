package ansk98.de.byteunbound.service.api.telegram;

import org.telegram.telegrambots.meta.api.objects.Document;

/**
 * Service to handle received attachments.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
public interface IAttachmentHandler {

    /**
     * Handles a received document.
     *
     * @param document document
     */
    void handle(Document document);

    /**
     * Checks if handling of the document is possible based on its mime type.
     *
     * @param mimeType document mime type
     * @return true if supported
     */
    boolean supportedMimeType(String mimeType);
}
