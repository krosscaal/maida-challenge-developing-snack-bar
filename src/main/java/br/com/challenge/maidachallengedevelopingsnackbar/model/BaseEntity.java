/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.model;

import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
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

  private OffsetDateTime createdAt;

  private OffsetDateTime updatedAt;

  @PrePersist
  protected void prePersist() {
    this.createdAt = OffsetDateTime.now(Clock.systemUTC());
  }

  @PreUpdate
  protected void preUpdate() {
    this.updatedAt = OffsetDateTime.now(Clock.systemUTC());
  }

}
