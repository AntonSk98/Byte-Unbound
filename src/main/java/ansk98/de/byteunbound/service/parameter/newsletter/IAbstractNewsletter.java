package ansk98.de.byteunbound.service.parameter.newsletter;

public interface IAbstractNewsletter {

    boolean isEmpty();

    default <Newsletter> Newsletter map(Class<Newsletter> clazz) {
        return clazz.cast(this);
    }
}
