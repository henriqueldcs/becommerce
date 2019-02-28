package br.com.becommerce.pms.repository;

import br.com.becommerce.pms.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {


    List<Product> findAllByReferenceCodeContaining(String referenceCode, Pageable pageable);


}
