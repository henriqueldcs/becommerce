package br.com.becommerce.ims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EntityScan
@ComponentScan(basePackageClasses = InventoryManagementServiceApplication.class)
@Import({InventoryManagementServiceConfig.class})
public class InventoryManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementServiceApplication.class, args);
	}

	@PostConstruct
	public void init() {
		//TODO: ADD LOG
	}
}
