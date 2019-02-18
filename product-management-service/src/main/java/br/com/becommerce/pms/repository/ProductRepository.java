package br.com.becommerce.pms.repository;

import br.com.becommerce.pms.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
