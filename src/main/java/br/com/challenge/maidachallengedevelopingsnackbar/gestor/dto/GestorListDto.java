/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.gestor.dto;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GestorListDto {

  public String nome;
  public String email;
  public String  telefone;
  public OffsetDateTime dataNascimento;
  private String nomeLanchonete;


}
