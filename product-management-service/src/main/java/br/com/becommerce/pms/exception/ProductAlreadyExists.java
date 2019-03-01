package br.com.becommerce.pms.exception;

public class ProductAlreadyExists extends Exception{

	public static final String MESSAGE = "ReferenceCode já cadastrado!";

	public ProductAlreadyExists() {
		super(MESSAGE);
	}

}
