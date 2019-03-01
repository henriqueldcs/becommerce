package br.com.becommerce.pms.exception;

import br.com.becommerce.commons.constants.MessageConstants;

public class ProductAlreadyExists extends Exception{



	public ProductAlreadyExists() {
		super(MessageConstants.REFERENCE_CODE_ALREADY_EXISTS);
	}

}
