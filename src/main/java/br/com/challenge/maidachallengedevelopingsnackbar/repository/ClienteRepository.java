/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.repository;

import br.com.challenge.maidachallengedevelopingsnackbar.cliente.ClienteEntity;
import br.com.challenge.maidachallengedevelopingsnackbar.produto.ProdutoEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

  Optional<ClienteEntity> findByEmail(String email);

}
