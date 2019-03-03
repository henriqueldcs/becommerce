package br.com.becommerce.ims.exception;

import br.com.becommerce.commons.constants.MessageConstants;

public class InventoryNotFound extends Exception {

	public InventoryNotFound() {
		super(MessageConstants.INVENTORY_NOT_FOUND);
	}
}