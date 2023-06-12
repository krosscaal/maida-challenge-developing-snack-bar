/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.itempedido;

import br.com.challenge.maidachallengedevelopingsnackbar.model.BaseEntity;
import br.com.challenge.maidachallengedevelopingsnackbar.pedido.PedidoEntity;
import br.com.challenge.maidachallengedevelopingsnackbar.produto.ProdutoEntity;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_items")
public class ItemPedidoEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  @Column(name = "preco_unitario")
  private BigDecimal precoUnitario;
  @NotNull
  private Integer quantidade;

  @ManyToOne(fetch = FetchType.LAZY)
  private PedidoEntity pedido;

  @ManyToOne(fetch = FetchType.LAZY)
  private ProdutoEntity produto;

  public ItemPedidoEntity(final Integer quantidade, final PedidoEntity pedido, final ProdutoEntity produto) {

    this.quantidade = quantidade;
    this.pedido = pedido;
    this.produto = produto;
    this.precoUnitario = produto.getPreco();
  }

  public BigDecimal getValordoItemPedido() {
    return precoUnitario.multiply(new BigDecimal(this.quantidade));
  }

}
