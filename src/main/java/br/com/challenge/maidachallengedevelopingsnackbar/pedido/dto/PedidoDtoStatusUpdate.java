/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.pedido.dto;

import br.com.challenge.maidachallengedevelopingsnackbar.domain.DomainOrderStatus;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PedidoDtoStatusUpdate {
  @NotNull
  private DomainOrderStatus status;


}
