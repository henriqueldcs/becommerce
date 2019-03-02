package br.com.becommerce.ims.exception;

import br.com.becommerce.commons.constants.MessageConstants;

public class InventoryAlreadyExists extends Exception {

	public InventoryAlreadyExists() {
		super(MessageConstants.REFERENCE_CODE_ALREADY_EXISTS);
	}
}
