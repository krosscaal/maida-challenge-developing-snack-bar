/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.itempedido.dto;

import br.com.challenge.maidachallengedevelopingsnackbar.pedido.PedidoEntity;
import br.com.challenge.maidachallengedevelopingsnackbar.produto.dto.ProdutoDtoParaCliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemPedidoDto {

  private ProdutoDtoParaCliente produto;
  private Integer quantidade;

}
