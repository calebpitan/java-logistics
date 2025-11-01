package com.calebpitan.logistics.fulfilmentcenter;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FulfilmentCenterRepository extends JpaRepository<FulfilmentCenter, Long> {
  Optional<FulfilmentCenter> findByName(String name);
}
