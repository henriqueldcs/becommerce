package br.com.becommerce.ims;

import br.com.becommerce.commons.BCConfig;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EntityScan
@Import(BCConfig.class)
@ComponentScan(basePackageClasses = {InventoryManagementServiceConfig.class})
@PropertySource(value = "classpath:ims-application.properties")
@PropertySource(value = "classpath:ims-application-${spring.profiles.active}.properties")
public class InventoryManagementServiceConfig {
}
