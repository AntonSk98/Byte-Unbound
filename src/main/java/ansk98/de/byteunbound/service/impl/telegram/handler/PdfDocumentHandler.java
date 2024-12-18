package ansk98.de.byteunbound.service.impl.telegram.handler;

import ansk98.de.byteunbound.service.api.telegram.IAttachmentHandler;
import ansk98.de.byteunbound.service.api.telegram.ITelegramClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Document;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class PdfDocumentHandler implements IAttachmentHandler {

    private final ITelegramClient telegramClient;

    public PdfDocumentHandler(ITelegramClient telegramClient) {
        this.telegramClient = telegramClient;
    }

    @Override
    public void handle(Document document) {
        telegramClient.sendMessageToBot("Processing the attachment...");
        try (InputStream binaryPdf = telegramClient.streamFile(document.getFileId())) {
            List<InputStream> binaryImages = convertPdfToImages(binaryPdf);
            telegramClient.sendArticle(StringUtils.substringBefore(document.getFileName(), "."), binaryImages);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean supportedMimeType(String mimeType) {
        return "application/pdf".equals(mimeType);
    }

    public static List<InputStream> convertPdfToImages(InputStream pdfInputStream) {
        List<InputStream> imageStreams = new ArrayList<>();

        try (PDDocument document = Loader.loadPDF(pdfInputStream.readAllBytes())) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);

            for (int page = 0; page < document.getNumberOfPages(); page++) {
                BufferedImage image = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);

                try (ByteArrayOutputStream imageOutputStream = new ByteArrayOutputStream()) {
                    ImageIO.write(image, "png", imageOutputStream);
                    imageStreams.add(new ByteArrayInputStream(imageOutputStream.toByteArray()));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while splitting pdf into images", e);
        }

        return imageStreams;
    }


}
