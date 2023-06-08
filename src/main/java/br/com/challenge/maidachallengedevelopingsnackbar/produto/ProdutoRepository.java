/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.produto;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

  Optional<ProdutoEntity> findByNome(String nome);

}
