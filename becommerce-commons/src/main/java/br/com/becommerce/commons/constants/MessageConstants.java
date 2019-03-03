package br.com.becommerce.commons.constants;

public class MessageConstants {

    /* API MESSAGES */
    public static final String CREATE_SUCCESS_MESSAGE = "Cadastro realizado com sucesso!";
    public static final String UPDATE_SUCCESS_MESSAGE = "Alteração realizada com sucesso!";
    public static final String DELETE_SUCCESS_MESSAGE = "Exclusão realizada com sucesso!";
    public static final String UNAUTHORIZED_API_KEY = "Api Key inválida!";



    /* EXCEPTION MESSAGES */
    public static final String REFERENCE_CODE_ALREADY_EXISTS = "Este item já foi cadastrado em ações anteriores!";
    public static final String PRODUCT_NOT_FOUND = "Produto não encontrado!";
    public static final String INVENTORY_NOT_FOUND = "Produto não encontrado em estoque!";
    public static final String VALUE_NOT_NULL = "Não pode ser nulo!";
    public static final String VALUE_GREATER_THAN_ZERO = "Deve ser maior que zero!";
    public static final String INVENTORY_LESS_THAN_ZERO = "O valor final do estoque não pode ser menor que zero!";
    public static final String INVALID_OPERATION = "Operação inválida! Operações permitidas: ";

}
