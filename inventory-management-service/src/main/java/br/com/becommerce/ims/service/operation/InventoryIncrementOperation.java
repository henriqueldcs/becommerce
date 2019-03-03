package br.com.becommerce.ims.service.operation;

import br.com.becommerce.ims.model.Inventory;

import java.math.BigDecimal;

public class InventoryIncrementOperation implements InventoryOperation {

	@Override
	public BigDecimal doCalculation(Inventory inventory, BigDecimal value) {
		return inventory.getAmount().add(value);
	}

	@Override
	public void doPostValidation(BigDecimal calculatedValue) {
		//NÃO VALIDA NADA.
	}

}
