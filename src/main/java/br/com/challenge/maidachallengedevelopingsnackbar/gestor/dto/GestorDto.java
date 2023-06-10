/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.gestor.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GestorDto implements Serializable {

  @NotNull
  @NotBlank
  @Size(min = 5, max = 100)
  public String nome;
  @NotNull
  @NotBlank
  @Email
  public String email;
  @NotNull
  @NotBlank
  @Length(min = 6, max = 100)
  public String senha;
  @NotNull
  public String telefone;
  @NotNull
  public OffsetDateTime dataNascimento;
  @NotNull
  @Size(min = 5, max = 100)
  private String nomeLanchonete;

}
