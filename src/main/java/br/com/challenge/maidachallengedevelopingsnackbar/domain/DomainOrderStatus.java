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
  RECEIVED("Recebido"),
  IN_PREPARATION("Em preparação"),
  READY("Pronto"),
  DELIVERED("Entregado"),
  CANCELED("Cancelado");

  @Getter
  @Setter
  private String desc;
}
