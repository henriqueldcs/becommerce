package br.com.becommerce.pms.service;

import br.com.becommerce.pms.exception.ProductAlreadyExists;
import br.com.becommerce.pms.exception.ProductNotFound;
import br.com.becommerce.pms.model.Product;
import br.com.becommerce.pms.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.becommerce.commons.constants.DatabaseConstants.PAGE_NUMBER_DEFAULT;
import static br.com.becommerce.commons.constants.DatabaseConstants.PAGE_SIZE_DEFAULT;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.base.Strings.nullToEmpty;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findProductBy(final String referenceCode, final Integer page, final Integer size) {

        final Integer pageSize = (size == null || size < 1) ? PAGE_SIZE_DEFAULT : size;
        final Integer pageNumber = (page == null || page < 0) ? PAGE_NUMBER_DEFAULT : page;

        return productRepository.findAllByReferenceCodeContaining(nullToEmpty(referenceCode), PageRequest.of(pageNumber, pageSize));
    }

	public void addProduct(final Product product) throws ProductAlreadyExists {

    	if(productRepository.existsByReferenceCode(product.getReferenceCode())) {
    		throw new ProductAlreadyExists();
		}

    	productRepository.save(product);
	}

	public void updateProduct(final Product product) throws ProductNotFound {

    	Product productFromDatabase = getProductOrException(product.getReferenceCode());

		if(!isNullOrEmpty(product.getDescription()))
			productFromDatabase.setDescription(product.getDescription());

		if(!isNullOrEmpty(product.getName()))
			productFromDatabase.setName(product.getName());

		productRepository.save(productFromDatabase);
	}

	public void deleteProduct(String referenceCode) throws ProductNotFound {



    	productRepository.delete(getProductOrException(referenceCode));
	}

	private Product getProductOrException(String referenceCode) throws ProductNotFound {

		Product productFromDatabase = productRepository.findByReferenceCode(referenceCode);
		if(productFromDatabase == null) {
			throw new ProductNotFound();
		}

		return productFromDatabase;
	}
}
