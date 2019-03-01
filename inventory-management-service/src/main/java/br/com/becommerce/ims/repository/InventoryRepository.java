package br.com.becommerce.ims.repository;

import br.com.becommerce.ims.model.Inventory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends PagingAndSortingRepository<Inventory, Integer> {

	List<Inventory> findAllByProductReferenceCodeContaining(String productReferenceCode, Pageable pageable);

}
