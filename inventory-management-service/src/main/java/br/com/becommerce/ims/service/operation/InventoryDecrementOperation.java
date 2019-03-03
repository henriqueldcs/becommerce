package br.com.becommerce.ims.service.operation;

import br.com.becommerce.commons.constants.MessageConstants;
import br.com.becommerce.ims.model.Inventory;
import br.com.becommerce.ims.service.operation.exception.InventoryOperationException;

import java.math.BigDecimal;

public class InventoryDecrementOperation implements InventoryOperation{

	@Override
	public void doPostValidation(BigDecimal calculatedValue) throws InventoryOperationException {
		if(BigDecimal.ZERO.compareTo(calculatedValue) > 0) {
			throw new InventoryOperationException(MessageConstants.INVENTORY_LESS_THAN_ZERO);
		}
	}

	@Override
	public BigDecimal doCalculation(Inventory inventory, BigDecimal value) {

		return inventory.getAmount().subtract(value);
	}
}
