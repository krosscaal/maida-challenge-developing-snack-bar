/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter
public class ApiErrors {

  private Integer status;
  private LocalDateTime dataHora;
  private String titulo;
  private List<Campo> campos;

  public ApiErrors(Integer status, LocalDateTime dataHora, String titulo) {
    this.status = status;
    this.dataHora = dataHora;
    this.titulo = titulo;
  }

  public ApiErrors(final Integer status, final LocalDateTime dataHora, final String titulo,
      final List<Campo> campos) {

    this.status = status;
    this.dataHora = dataHora;
    this.titulo = titulo;
    this.campos = campos;
  }

  @AllArgsConstructor
  @Getter
  public static class Campo {
    private String nome;
    private String mensagem;
  }

}
