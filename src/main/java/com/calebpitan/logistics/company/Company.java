package com.calebpitan.logistics.company;

import com.calebpitan.logistics.fulfilmentcenter.FulfilmentCenter;
import com.calebpitan.logistics.user.User;
import com.calebpitan.logistics.utils.entity.AbstractAuditable;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Company extends AbstractAuditable<User, Long> {
  @NotBlank(message = "Name cannot be blank")
  private String name;

  @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<FulfilmentCenter> fulfilmentCenters = new ArrayList<>();

  public void addFulfilmentCenter(FulfilmentCenter fc) {
    this.fulfilmentCenters.add(fc);
    fc.setCompany(this);
  }
}
