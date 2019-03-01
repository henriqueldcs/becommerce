package br.com.becommerce.pms.exception;

public class ProductNotFound extends Exception{

	public static final String MESSAGE = "Produto não encontrado!";

	public ProductNotFound() {
		super(MESSAGE);
	}

}
