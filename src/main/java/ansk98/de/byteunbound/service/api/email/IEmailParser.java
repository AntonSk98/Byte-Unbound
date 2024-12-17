package ansk98.de.byteunbound.service.api.email;

import jakarta.mail.Message;

import java.util.List;

public interface IEmailParser {

    List<String> extractLinks(Message message);
}
