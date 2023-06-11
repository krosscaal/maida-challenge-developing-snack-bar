/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.pedido;

import br.com.challenge.maidachallengedevelopingsnackbar.cliente.ClienteEntity;
import br.com.challenge.maidachallengedevelopingsnackbar.domain.DomainOrderStatus;
import br.com.challenge.maidachallengedevelopingsnackbar.model.BaseEntity;
import br.com.challenge.maidachallengedevelopingsnackbar.produto.ProdutoEntity;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
@Table(name = "snack_bar_orders")
public class PedidoEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "costumer_id",
      referencedColumnName = "id",
      foreignKey = @ForeignKey(name = "fk_order_costumer_id"))
  private ClienteEntity cliente;

  @ManyToMany(
      cascade = CascadeType.MERGE,
      fetch = FetchType.LAZY)
  @JoinTable(name = "orders_products",
      joinColumns = {@JoinColumn(name = "order_id")},
      inverseJoinColumns = {@JoinColumn(name = "product_id")})
  private List<ProdutoEntity> produtos = new ArrayList<>();

  @NotNull
  @Column(name = "order_date")
  private OffsetDateTime dataPedido;

  @NotNull
  @Column(name = "total_price")
  private BigDecimal valor;

  @NotNull
  @Enumerated(EnumType.STRING)
  private DomainOrderStatus status;
}
