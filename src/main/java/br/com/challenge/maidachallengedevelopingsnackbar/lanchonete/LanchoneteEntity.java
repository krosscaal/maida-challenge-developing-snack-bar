/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.lanchonete;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import br.com.challenge.maidachallengedevelopingsnackbar.gestor.GestorEntity;
import br.com.challenge.maidachallengedevelopingsnackbar.model.BaseEntity;
import br.com.challenge.maidachallengedevelopingsnackbar.model.User;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "snack_bar")
public class LanchoneteEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  @NotNull
  @NotBlank
  @Length(min = 5 , max = 255)
  private String nome;

  @NotNull
  @NotBlank
  @Email
  private String email;

  @Column(name = "foneNumber")
  private Integer telefone;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "manager_id",
      referencedColumnName = "id",
      foreignKey = @ForeignKey(name = "fk_snack_manager_id"))
  private GestorEntity gestor;


}
