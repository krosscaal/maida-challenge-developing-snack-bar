/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.cliente.dto;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public class ClienteDto {

  @NotNull
  @NotBlank
  @Size(min = 100)
  public String nome;
  @NotNull
  @NotBlank
  @Size(max = 100)
  public String email;
  @NotNull
  @NotBlank
  @Length(min = 6, max = 100)
  public String senha;
  @NotNull
  @Size(max = 15)
  public Integer telefone;
  @NotNull
  public Date dataNascimento;


}
