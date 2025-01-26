package ansk98.de.byteunbound.service.impl.email;

import ansk98.de.byteunbound.properties.MediumNewsletterProperties;
import ansk98.de.byteunbound.service.api.email.IEmailParser;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.internet.MimeMultipart;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Implementation of {@link IEmailParser}.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
@Component
public class EmailParser implements IEmailParser {

    private final MediumNewsletterProperties mediumNewsletterProperties;

    public EmailParser(MediumNewsletterProperties mediumNewsletterProperties) {
        this.mediumNewsletterProperties = mediumNewsletterProperties;
    }

    @Override
    public List<String> extractLinks(Message message) {
        String emailText = extractAllTextFrom(message);
        Document doc = Jsoup.parse(emailText);
        Pattern articlePatter = Pattern.compile(mediumNewsletterProperties.articlePattern());
        return doc.select("a[href]")
                .stream()
                .map(element -> element.attr("href"))
                .filter(link -> articlePatter.matcher(link).matches())
                .map(link -> StringUtils.substringBefore(link, mediumNewsletterProperties.substringBefore()))
                .distinct()
                .toList();
    }

    private static String extractAllTextFrom(Message message) {
        try {
            if (message.isMimeType("text/plain")) {
                return message.getContent().toString();
            } else if (message.isMimeType("text/html")) {
                return message.getContent().toString();
            } else if (message.isMimeType("multipart/*")) {
                MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
                return extractTextFromMimeMultipart(mimeMultipart);
            }
            return Strings.EMPTY;
        } catch (Exception e) {
            throw new RuntimeException("Could not extract text from email message", e);
        }
    }

    private static String extractTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
        StringBuilder result = new StringBuilder();
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result.append(bodyPart.getContent().toString());
            } else if (bodyPart.isMimeType("text/html")) {
                result.append(bodyPart.getContent().toString());
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result.append(extractTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
            }
        }
        return result.toString();
    }
}
