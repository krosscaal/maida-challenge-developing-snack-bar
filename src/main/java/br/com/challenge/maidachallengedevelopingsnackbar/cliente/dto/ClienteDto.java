/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.cliente.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import javax.json.bind.annotation.JsonbDateFormat;
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
@Getter
@Setter
public class ClienteDto implements Serializable {

  @NotNull
  @NotBlank
  @Size(min = 4, max = 100)
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

  public ClienteDto() { }

}
