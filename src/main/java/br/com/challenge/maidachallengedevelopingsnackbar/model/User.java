/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@MappedSuperclass
public abstract class User extends BaseEntity{

  @Column(name = "name")
  @NotBlank
  @Size(max = 255)
  private String nome;

  @Email
  @NotBlank
  @Size(max = 255)
  private String email;

  @Column(name = "password")
  @NotBlank
  @Length(min = 6, max = 255)
  private String senha;

  @Column(name = "fone_number")
  @NotBlank
  @Size(max = 15)
  private Integer telefone;

  @NotBlank
  @Column(name = "birth_date")
  private Date datanascimento;

}
