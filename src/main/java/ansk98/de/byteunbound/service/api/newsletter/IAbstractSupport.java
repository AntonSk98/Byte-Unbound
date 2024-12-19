package ansk98.de.byteunbound.service.api.newsletter;

/**
 * Determines if a service is supported by newsletter published source.
 *
 * @author Anton Skripin (anton.tech98@gmail.com)
 */
public interface IAbstractSupport {

    /**
     * Is newsletter service supported based on the passed source?
     * @param source source
     * @return true if service is supported
     */
    boolean supports(Class<?> source);
}
