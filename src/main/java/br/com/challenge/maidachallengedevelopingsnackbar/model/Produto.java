/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.model;

import br.com.challenge.maidachallengedevelopingsnackbar.domain.ProductClassification;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Produto extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  @NotBlank
  @Size(max = 255)
  private String nome;

  @Column(name = "product_price")
  @NotBlank
  @NotBlank
  private BigDecimal preco;

  @Column(name = "description")
  @NotBlank
  @Size(max = 300)
  private String descricao;

  @Enumerated(EnumType.STRING)
  @Column(name = "product_type")
  private ProductClassification tipo;

}
