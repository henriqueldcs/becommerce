package br.com.becommerce.ims.service.operation;

import br.com.becommerce.commons.to.InventoryProductAction;
import br.com.becommerce.ims.model.Inventory;
import br.com.becommerce.ims.service.operation.exception.InventoryOperationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
public class InventoryIncrementOperationTest {

	public InventoryOperation operation;
	public Inventory inventory;

	@Before
	public void init() {
		operation = InventoryOperation.getOperator(InventoryProductAction.ActionEnum.INCREMENT).get();
		inventory = Inventory.builder().productReferenceCode("P1").build();
	}

	@Test
	public void incrementBalance() throws InventoryOperationException {

		final BigDecimal expectedValue = BigDecimal.TEN;
		inventory.setAmount(BigDecimal.ZERO);

		final BigDecimal result = operation.calculateNewValue(inventory, BigDecimal.TEN);

		Assert.assertEquals(expectedValue, result);
	}

	@Test(expected = InventoryOperationException.class)
	public void tryIncrementWithNullAmount() throws InventoryOperationException {

		inventory.setAmount(BigDecimal.ONE);

		operation.calculateNewValue(inventory, null);

	}

	@Test(expected = InventoryOperationException.class)
	public void tryIncrementWithNegativeAmount() throws InventoryOperationException {

		inventory.setAmount(BigDecimal.ONE);

		operation.calculateNewValue(inventory, BigDecimal.TEN.negate());

	}


}
