package br.com.becommerce.api.gateway;

import br.com.becommerce.commons.BCConfig;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EntityScan
@ComponentScan(basePackageClasses = {ApiGatewayConfig.class})
@Import(BCConfig.class)
@PropertySource(value = "classpath:api-gateway-application.properties")
@PropertySource(value = "classpath:api-gateway-application-${spring.profiles.active}.properties")
public class ApiGatewayConfig {
}
