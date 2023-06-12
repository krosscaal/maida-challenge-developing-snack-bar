/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.pedido;

import br.com.challenge.maidachallengedevelopingsnackbar.cliente.ClienteEntity;
import br.com.challenge.maidachallengedevelopingsnackbar.domain.DomainOrderStatus;
import br.com.challenge.maidachallengedevelopingsnackbar.itempedido.ItemPedidoEntity;
import br.com.challenge.maidachallengedevelopingsnackbar.model.BaseEntity;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
@Table(name = "snack_bar_orders")
public class PedidoEntity extends BaseEntity {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "costumer_id",
      referencedColumnName = "id",
      foreignKey = @ForeignKey(name = "fk_order_costumer_id"))
  private ClienteEntity cliente;

  @OneToMany(mappedBy = "pedido", cascade = CascadeType.MERGE)
  private List<ItemPedidoEntity> itens = new ArrayList<>();
  @NotNull
  @Column(name = "order_date")
  private OffsetDateTime dataPedido;

  @NotNull
  @Column(name = "total_price")
  private BigDecimal valor = BigDecimal.ZERO;

  @NotNull
  @Enumerated(EnumType.STRING)
  private DomainOrderStatus status;

  public PedidoEntity(final ClienteEntity cliente) {

    this.cliente = cliente;
  }
  public void adicionarItem(ItemPedidoEntity item) {
    item.setPedido(this);
    this.itens.add(item);
    this.valor = this.valor.add(item.getValordoItemPedido());
  }
  public void removerIten(ItemPedidoEntity item) {
    this.itens.remove(item);
  }

}
