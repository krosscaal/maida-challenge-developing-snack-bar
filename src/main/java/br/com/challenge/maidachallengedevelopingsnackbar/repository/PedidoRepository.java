/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.repository;

import br.com.challenge.maidachallengedevelopingsnackbar.domain.DomainOrderStatus;
import br.com.challenge.maidachallengedevelopingsnackbar.pedido.PedidoEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends PagingAndSortingRepository<PedidoEntity, Long>,
    JpaSpecificationExecutor<PedidoEntity> {

  @Query(value = "SELECT p FROM PedidoEntity p WHERE p.status = :status", nativeQuery = true)
  List<PedidoEntity> findByStatus(DomainOrderStatus status);
}
