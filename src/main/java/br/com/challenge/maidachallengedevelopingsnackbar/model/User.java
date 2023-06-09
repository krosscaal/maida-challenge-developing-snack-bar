/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@MappedSuperclass
public abstract class User extends BaseEntity{

  @Column(name = "name")
  private String nome;

  private String email;

  @Column(name = "password")
  private String senha;

  @Column(name = "fone_number")
  private String telefone;

  @Column(name = "birth_date")
  private Date dataNascimento;

}
