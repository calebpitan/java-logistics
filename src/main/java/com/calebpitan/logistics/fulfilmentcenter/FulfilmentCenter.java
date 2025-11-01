package com.calebpitan.logistics.fulfilmentcenter;

import com.calebpitan.logistics.company.Company;
import com.calebpitan.logistics.user.User;
import com.calebpitan.logistics.utils.entity.AbstractAuditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FulfilmentCenter extends AbstractAuditable<User, Long> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Company company;
}
