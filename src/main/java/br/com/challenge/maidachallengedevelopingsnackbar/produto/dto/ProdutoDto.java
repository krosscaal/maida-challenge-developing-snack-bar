/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.produto.dto;

import br.com.challenge.maidachallengedevelopingsnackbar.domain.DomainProduct;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProdutoDto implements Serializable {

  @NotNull
  @NotBlank
  @Size(min = 5, max = 100)
  private String nome;

  @NotNull
  @NotBlank
  @Size(min = 10, max = 255)
  private String descricao;

  @NotNull
  private DomainProduct tipo;

  @NotNull
  private Integer quantidade;

  @NotNull
  @Min(3)
  private Integer quantidadeMinima;

  @NotNull
  private BigDecimal preco;

}
