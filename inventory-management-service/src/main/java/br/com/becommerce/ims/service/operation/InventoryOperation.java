package br.com.becommerce.ims.service.operation;

import br.com.becommerce.commons.constants.MessageConstants;
import br.com.becommerce.commons.to.InventoryProductAction;
import br.com.becommerce.ims.model.Inventory;
import br.com.becommerce.ims.service.operation.exception.InventoryOperationException;
import com.google.common.collect.ImmutableMap;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public interface InventoryOperation {


	/**
	 * Map de @{@link InventoryOperation} para cada @{@link br.com.becommerce.commons.to.InventoryProductAction.ActionEnum}
	 */
	Map<InventoryProductAction.ActionEnum, InventoryOperation> mapOperation = ImmutableMap.of(
			InventoryProductAction.ActionEnum.INCREMENT, new InventoryIncrementOperation(),
			InventoryProductAction.ActionEnum.DECREMENT, new InventoryDecrementOperation()
	);


	/**
	 * Calculo de novo valor baseado an operação.
	 * @param inventory item a calcular novo valor.
	 * @param value valor a ser incrementado/removido.
	 * @return Novo valor calculado.
	 */
	default BigDecimal calculateNewValue(Inventory inventory, BigDecimal value) throws InventoryOperationException {

		validateGreaterThanZero(value);
		BigDecimal calculatedValue = doCalculation(inventory, value);
		doPostValidation(calculatedValue);
		return calculatedValue;
	}

	void doPostValidation(BigDecimal calculatedValue) throws InventoryOperationException;

	BigDecimal doCalculation(Inventory inventory, BigDecimal value);

	default void validateGreaterThanZero(BigDecimal value) throws InventoryOperationException {

		if(value == null) {
			throw new InventoryOperationException("value: " + MessageConstants.VALUE_NOT_NULL);
		}

		if(BigDecimal.ZERO.compareTo(value) >= 0) {
			throw new InventoryOperationException("value: " + MessageConstants.VALUE_GREATER_THAN_ZERO);
		}
	}

	/**
	 * Retorna implemntação de @{@link InventoryOperation} baseado no @{@link br.com.becommerce.commons.to.InventoryProductAction.ActionEnum} passado.
	 * @param actionEnum Enum passado para determinar operação.
	 * @return @{@link Optional} de operação selecionada.
	 */
	static Optional<InventoryOperation> getOperator(InventoryProductAction.ActionEnum actionEnum) {
		if(mapOperation.containsKey(actionEnum)) {
			return Optional.of(mapOperation.get(actionEnum));
		}
		return Optional.empty();
	}
}
