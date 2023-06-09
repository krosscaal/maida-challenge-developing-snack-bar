/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.produto.dto;

import br.com.challenge.maidachallengedevelopingsnackbar.domain.DomainProduct;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProdutoDtoParaCliente {

  private Long id;

  private String nome;

  private String descricao;

  private DomainProduct tipo;

  private BigDecimal preco;


}
