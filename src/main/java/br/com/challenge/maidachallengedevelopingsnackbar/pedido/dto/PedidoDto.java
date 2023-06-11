/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.pedido.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PedidoDto implements Serializable {

  @NotNull
  private Long cliente_id;
  @NotNull
  private List<Long> produtos = new ArrayList<>();
  @NotNull
  private OffsetDateTime dataPedido;

}
