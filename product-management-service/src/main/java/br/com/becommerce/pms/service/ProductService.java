package br.com.becommerce.pms.service;

import br.com.becommerce.pms.model.Product;
import br.com.becommerce.pms.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.becommerce.commons.constants.DatabaseConstants.PAGE_NUMBER_DEFAULT;
import static br.com.becommerce.commons.constants.DatabaseConstants.PAGE_SIZE_DEFAULT;
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

}
