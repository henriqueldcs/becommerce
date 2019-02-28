package br.com.becommerce.commons;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EntityScan
@ComponentScan(basePackageClasses = {BCConfig.class})
@PropertySource(value = "classpath:api-gateway-application.properties")
public class BCConfig {
}
