/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.produto.dto;

import br.com.challenge.maidachallengedevelopingsnackbar.domain.DomainProduct;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import javax.json.bind.annotation.JsonbDateFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProdutoDtoParaGestor implements Serializable {

  private Long id;

  @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  private OffsetDateTime createdAt;

  @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  private OffsetDateTime updatedAt;

  private String nome;
  private String descricao;
  private DomainProduct tipo;
  private Integer quantidade;
  private Integer quantidadeMinima;
  private BigDecimal preco;

}
