package br.com.becommerce.pms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EntityScan
@ComponentScan(basePackageClasses = ProductManagementServiceApplication.class)
@Import({ProductManagementServiceConfig.class})
public class ProductManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductManagementServiceApplication.class, args);
	}

	@PostConstruct
	public void init() {
		//TODO: ADD LOG
	}
}
