/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.pedido.dto;

import br.com.challenge.maidachallengedevelopingsnackbar.domain.DomainOrderStatus;
import br.com.challenge.maidachallengedevelopingsnackbar.itempedido.dto.ItemPedidoDto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PedidoDtoStatus implements Serializable {
  private Long id;
  private List<ItemPedidoDto> produtos = new ArrayList<>();
  private BigDecimal valor;
  private DomainOrderStatus status;

}
