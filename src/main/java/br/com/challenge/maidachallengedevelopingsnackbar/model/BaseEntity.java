/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

  private static final long serialVersionID = 1L;

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
