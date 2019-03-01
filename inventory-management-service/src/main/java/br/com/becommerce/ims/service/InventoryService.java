package br.com.becommerce.ims.service;

import br.com.becommerce.ims.model.Inventory;
import br.com.becommerce.ims.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.becommerce.commons.constants.DatabaseConstants.PAGE_NUMBER_DEFAULT;
import static br.com.becommerce.commons.constants.DatabaseConstants.PAGE_SIZE_DEFAULT;
import static com.google.common.base.Strings.nullToEmpty;

@Service
public class InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;

	public List<Inventory> findInventoryBy(final String productReferenceCode, final Integer page, final Integer size) {

		final Integer pageSize = (size == null || size < 1) ? PAGE_SIZE_DEFAULT : size;
		final Integer pageNumber = (page == null || page < 0) ? PAGE_NUMBER_DEFAULT : page;

		return inventoryRepository.findAllByProductReferenceCodeContaining(nullToEmpty(productReferenceCode), PageRequest.of(pageNumber, pageSize));
	}

}
