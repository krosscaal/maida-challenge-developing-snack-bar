/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.exception;

public class BusinessException extends RuntimeException{

  private static final long serialVersionUID = 1L;

  public BusinessException(String mensagem) {
    super(mensagem);
  }

}
