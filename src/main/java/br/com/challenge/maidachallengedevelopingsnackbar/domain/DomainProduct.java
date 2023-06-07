/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum DomainProduct {
  DRINK("Bebida"),
  FOOD("Comida"),
  DESSERT("Sobremesa");

  @Getter
  @Setter
  private String desc;
}
