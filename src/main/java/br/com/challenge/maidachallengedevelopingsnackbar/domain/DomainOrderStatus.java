/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum DomainOrderStatus {
  REQUESTED("PEDIDO SOLICITADO"),
  IN_PREPARATION("PEDIDO EM PREPARAÇÃO"),
  READY("PEDIDO PRONTO"),
  DELIVERED("PEDIDO ENTREGADO"),
  CANCELED("CANCELADO POR USUARIO"),
  RECUSED("RECUSADO PELO GESTOR");


  @Getter
  @Setter
  private String desc;
}
