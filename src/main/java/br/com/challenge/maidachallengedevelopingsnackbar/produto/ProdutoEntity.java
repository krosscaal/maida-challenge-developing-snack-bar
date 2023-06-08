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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Getter
@Setter
@Entity
@Table(name = "products")
public class ProdutoEntity extends BaseEntity {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  @NotNull
  @NotBlank
  @Size(min = 5, max = 100)
  private String nome;

  @Column(name = "product_price")
  @NotNull
  private BigDecimal preco;

  @Column(name = "description")
  @NotNull
  @NotBlank
  @Size(min = 10, max = 255)
  private String descricao;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "product_type")
  private DomainProduct tipo;

  @NotNull
  @Column(name = "quantity")
  private Integer quantidade;

  @Column(name = "minimal_quantity")
  @Min(3)
  private Integer quantidadeMinima;

}
