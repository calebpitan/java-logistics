package com.calebpitan.logistics.utils.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Optional;

import lombok.NonNull;
import org.springframework.data.domain.Auditable;

@MappedSuperclass
public abstract class AbstractAuditable<U, PK extends Serializable> extends AbstractPersistable<PK>
    implements Auditable<U, PK, OffsetDateTime> {
  @Temporal(TemporalType.TIMESTAMP)
  @Nullable
  private OffsetDateTime createdAt;

  @ManyToOne @Nullable private U createdBy;

  @Temporal(TemporalType.TIMESTAMP)
  @Nullable
  private OffsetDateTime modifiedAt;

  @ManyToOne @Nullable private U modifiedBy;

  @NonNull
  public Optional<U> getCreatedBy() {
    return Optional.ofNullable(createdBy);
  }

  public void setCreatedBy(@Nullable U createdBy) {
    this.createdBy = createdBy;
  }

  @NonNull
  public Optional<OffsetDateTime> getCreatedDate() {
    return Optional.ofNullable(this.createdAt);
  }

  public void setCreatedDate(@NonNull OffsetDateTime creationDate) {
    this.createdAt = creationDate;
  }

  @NonNull
  public Optional<U> getLastModifiedBy() {
    return Optional.ofNullable(modifiedBy);
  }

  public void setLastModifiedBy(@Nullable U lastModifiedBy) {
    this.modifiedBy = lastModifiedBy;
  }

  @NonNull
  public Optional<OffsetDateTime> getLastModifiedDate() {
    return Optional.ofNullable(this.modifiedAt);
  }

  public void setLastModifiedDate(@Nullable OffsetDateTime lastModifiedDate) {
    this.modifiedAt = lastModifiedDate;
  }
}
