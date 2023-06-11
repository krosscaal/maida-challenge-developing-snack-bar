/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.produto.dto;

import br.com.challenge.maidachallengedevelopingsnackbar.domain.DomainProduct;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProdutoDtoParaSolicitarPedido implements Serializable {

  @NotNull
  private Long id;
  @NotNull
  private BigDecimal preco;


}
