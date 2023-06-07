/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.produto;

import br.com.challenge.maidachallengedevelopingsnackbar.domain.DomainProduct;
import br.com.challenge.maidachallengedevelopingsnackbar.model.BaseEntity;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "products")
public class ProdutoEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  @NotNull
  @NotBlank
  @Size(max = 100)
  private String nome;

  @Column(name = "product_price")
  @NotNull
  @NotBlank
  private BigDecimal preco;

  @Column(name = "description")
  @NotNull
  @NotBlank
  @Size(max = 255)
  private String descricao;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "product_type")
  private DomainProduct tipo;

  @NotNull
  @Column(name = "quantity")
  private Integer quantidade;

  @NotNull
  @Column(name = "minimal_quantity")
  private Integer quantidadeMinima;

}
