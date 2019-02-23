package br.com.becommerce.product.management.service;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EntityScan
@ComponentScan(basePackageClasses = {ProductManagementServiceConfig.class})
@PropertySource(value = "classpath:pms-application.properties")
@PropertySource(value = "classpath:pms-application-${spring.profiles.active}.properties")
public class ProductManagementServiceConfig {
}
