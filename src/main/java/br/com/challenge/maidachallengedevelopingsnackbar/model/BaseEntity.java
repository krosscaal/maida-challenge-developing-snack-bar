/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.model;

import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;
//import javax.persistence.Column;
//import javax.persistence.PrePersist;
//import javax.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public abstract class BaseEntity implements Serializable {

  private static final long serialVersionID = -7294537041577127962L;

  @Column(nullable = true, columnDefinition = "TIMESTAMP")
  private LocalDateTime createdAt;

  @Column(nullable = true, columnDefinition = "TIMESTAMP")
  private LocalDateTime updatedAt;

  @PrePersist
  protected void prePersist() {
    this.createdAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void preUpdate() {
    this.updatedAt = LocalDateTime.now();
  }

}
