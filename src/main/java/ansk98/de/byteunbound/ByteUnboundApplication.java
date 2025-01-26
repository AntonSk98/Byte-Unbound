package ansk98.de.byteunbound;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ConfigurationPropertiesScan
public class ByteUnboundApplication {

    public static void main(String[] args) {
        SpringApplication.run(ByteUnboundApplication.class, args);
    }

}
