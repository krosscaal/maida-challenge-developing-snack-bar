/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.repository;

import br.com.challenge.maidachallengedevelopingsnackbar.gestor.GestorEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GestorRepository extends JpaRepository<GestorEntity, Long> {

}
