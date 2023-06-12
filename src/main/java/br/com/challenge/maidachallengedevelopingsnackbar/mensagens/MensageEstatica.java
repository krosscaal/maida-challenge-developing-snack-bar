/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.mensagens;

public class MensageEstatica {
  public final static String PRODUCT_EXISTS = "Já existe um produto cadastrado com esse nome!";
  public final static String PRODUCT_NOT_FOUND = "Produto não encontrado!";
  public final static String PRODUCT_NAME_ERROR = "Campo nome somente pode conter letras y números sem caracteres especiais, minimo 5 caracteres sem contar espaços em branco";
  public final static String PRODUCT_QUANTITY_ERROR = "Campo quantidade deve ser maior o igual ao campo quantidadeMinima";
  public final static String PRODUCT_PRICE_ERROR = "Campo preço deve ser maior a zero";
  public final static String MANAGER_EXISTS ="Gestor já foi cadastrado, somente pode cadastrar um único Gestor!";
  public final static String MANAGER_NOT_FOUND = "Gestor não encontrado, necesario cadastrar um Gestor para continuar!";
  public final static String MANAGER_NAME_ERROR = "Campo nome deve conter somente letras sem carateres especiais, mínimo 5 caracteres sem contar espaços em branco!";
  public final static String TELEFONE_ERROR = "Campo telefone deve conter somente números!";
  public final static String COSTUMER_EMAIL_EXISTS ="Já existe um cliente cadastrado com e-mail informado!";
  public final static String COSTUMER_NOT_FOUND = "Cliente não foi encontrado!";
  public final static String COSTUMER_NAME_ERROR = "Campo nome deve conter somente letras sem carateres especiais, mínimo 4 caracteres sem contar espaços em branco!";
  public final static String ORDER_NOT_FOUND = "Pedido não encontrado!";
  public final static String ORDER_CANNOT_MODIFY_STATUS = "Pedido encontra-se com status CANCELADO, RECUSADO ou ENTREGADO e não pode ser alterado!";
  public final static String ORDER_IS_NOT_IN_REQUESTED_STATUS = "Pedido não encontra-se com status SOLICITADO e não pode ser cancelado";
  public final static String ORDER_CANNOT_BE_MODIFIED = "Pedido não encontra-se com status SOLICITADO e não pode ser modifcado!";
  public final static String ORDER_DOES_NOT_BELONG_TO_THE_CUSTOMER = "Este pedido não pertence ao cliente informado!";
  public final static String QUANTITY_ERROR = "Quantidade deve ser maior a zero ";
}
