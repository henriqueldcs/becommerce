package br.com.becommerce.commons;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {BCConfig.class})
public class BCConfig {
}
