/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.cliente.dto;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClienteDtoVerDadosPeloGestor {

  private LocalDateTime createdAt;
  private LocalDateTime updateAt;
  private String nome;
  private String email;
  private String telefone;
  private Date dataNascimento;

}
