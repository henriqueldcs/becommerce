package br.com.becommerce.pms.exception;

import br.com.becommerce.commons.constants.MessageConstants;

public class ProductNotFound extends Exception{

	public ProductNotFound() {
		super(MessageConstants.PRODUCT_NOT_FOUND);
	}

}
