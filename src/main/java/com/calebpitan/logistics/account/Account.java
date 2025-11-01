package com.calebpitan.logistics.account;

import com.calebpitan.logistics.user.User;
import com.calebpitan.logistics.utils.entity.AbstractAuditable;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account extends AbstractAuditable<User, Long> {
  @Nullable private String password;

  @ManyToOne
  @JoinColumn(nullable = false)
  private User user;

  @Temporal(TemporalType.TIMESTAMP)
  @Nullable
  private OffsetDateTime verifiedAt;

  @Temporal(TemporalType.TIMESTAMP)
  @ColumnDefault(value = "CURRENT_TIMESTAMP")
  @Nullable
  private OffsetDateTime lastAccessedAt;
}
