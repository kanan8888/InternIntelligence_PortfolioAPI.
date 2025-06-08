package az.portfolioapi.configuration;

import az.portfolioapi.config.properties.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class PropertiesConfig {

}
