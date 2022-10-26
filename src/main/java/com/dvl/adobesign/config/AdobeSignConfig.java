package com.dvl.adobesign.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "bean")
@Getter
@Setter
public class AdobeSignConfig {

    private String message = "Default message from backend is: %s <br/> Services : %s";

}
