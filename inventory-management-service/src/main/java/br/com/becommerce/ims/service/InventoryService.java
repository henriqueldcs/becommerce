package br.com.becommerce.ims.service;

import br.com.becommerce.commons.to.InventoryProduct;
import br.com.becommerce.commons.to.Product;
import br.com.becommerce.ims.exception.InventoryAlreadyExists;
import br.com.becommerce.ims.model.Inventory;
import br.com.becommerce.ims.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static br.com.becommerce.commons.constants.DatabaseConstants.PAGE_NUMBER_DEFAULT;
import static br.com.becommerce.commons.constants.DatabaseConstants.PAGE_SIZE_DEFAULT;
import static br.com.becommerce.commons.util.RequestUtil.doGetListProduct;
import static com.google.common.base.Strings.nullToEmpty;

@Service
@Slf4j
public class InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;

	/**
	 * Consulta de produtos no inventário.
	 * @param productReferenceCode Código do produto.
	 * @param page Página a buscar.
	 * @param size Tamanho máximo da pagina.
	 * @param apiKey Token de autenticação.
	 * @param requestUUID UUID da requisição utilizado para tracking.
	 * @return {@link InventoryProduct}
	 */
	public List<InventoryProduct> findInventoryProductBy(final String productReferenceCode, final Integer page, final Integer size, final String apiKey, final String requestUUID) {

		final Integer pageSize = (size == null || size < 1) ? PAGE_SIZE_DEFAULT : size;
		final Integer pageNumber = (page == null || page < 0) ? PAGE_NUMBER_DEFAULT : page;

		var inventories = inventoryRepository.findAllByProductReferenceCodeContaining(nullToEmpty(productReferenceCode), PageRequest.of(pageNumber, pageSize));

		return inventories.stream()
				.map(i -> InventoryProduct.builder()
						.amount(i.getAmount())
						.product(getProduct(i.getProductReferenceCode(), requestUUID, apiKey))
						.build())
				.collect(Collectors.toList());
	}


	/**
	 * Consulta API de produtos.
	 * @param productReferenceCode Código do produto.
	 * @param requestUUID UUID da requisição utilizado para tracking.
	 * @param apiKey Token de autenticação.
	 * @return {@link Product}
	 */
	private Product getProduct(final String productReferenceCode, final String requestUUID, final String apiKey){

		final String urlWithParams = "http://localhost:5005/products" + String.format("?%s=%s",
				"referenceCode", nullToEmpty(productReferenceCode) //
		);

		log.info(String.format("m=getProduct, api_key=%s, requestUUID=%s, productReferenceCode=%s, url=%s",
				apiKey, requestUUID, productReferenceCode, urlWithParams));

		try {
			final ResponseEntity<List<Product>> response = doGetListProduct(urlWithParams, Map.of("api_key", apiKey, "requestUUID", requestUUID));
			if(response == null || response.getBody().isEmpty()) {
				return Product.builder().referenceCode(productReferenceCode).build();
			}

			return response.getBody().stream()
					.filter(p -> Objects.equals(productReferenceCode, p.getReferenceCode()))
					.findFirst()
					.orElse(Product.builder().referenceCode(productReferenceCode).build());
		} catch (HttpClientErrorException e) {

			log.info(String.format("m=getProduct, code=%s, message=%s, api_key=%s, requestUUID=%s, productReferenceCode=%s, url=%s",
					e.getStatusCode(), e.getResponseBodyAsString(), apiKey, requestUUID, productReferenceCode, urlWithParams));
			return Product.builder().referenceCode(productReferenceCode).build();
		}
	}

	/**
	 * Adiciona um produto ao inventário.
	 * @param inventory item a ser adicionado.
	 * @throws InventoryAlreadyExists ocorre caso o produto já esteja cadastrado em estoque.
	 */
	public void addInventory(final Inventory inventory) throws InventoryAlreadyExists {

		if(inventoryRepository.existsByProductReferenceCode(inventory.getProductReferenceCode())) {
				throw new InventoryAlreadyExists();
			}

		inventoryRepository.save(inventory);
	}
}
