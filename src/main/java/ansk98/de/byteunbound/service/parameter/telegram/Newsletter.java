package ansk98.de.byteunbound.service.parameter.telegram;

import java.util.List;

/**
 * Ready-to-be-sent newsletter.
 *
 * @param posts list of posts
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
public record Newsletter(List<Post> posts) {
}
