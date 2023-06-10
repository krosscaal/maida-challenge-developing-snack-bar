/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.cliente.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.json.bind.annotation.JsonbDateFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClienteDtoDadosParaGestor implements Serializable {

  private Long id;
  private OffsetDateTime createdAt;

  private OffsetDateTime updatedAt;

  private String nome;
  private String email;
  private String telefone;

  private OffsetDateTime dataNascimento;

}
