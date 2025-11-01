package com.calebpitan.logistics.fulfilmentcenter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FulfilmentCenterService {
  private final FulfilmentCenterRepository fulfilmentCenterRepository;

  public FulfilmentCenter createFulfilmentCenter(String name, String address) {
    FulfilmentCenter fc = new FulfilmentCenter();
    fc.setName(name);
    fc.setAddress(address);

    return this.fulfilmentCenterRepository.save(fc);
  }

  public Iterable<FulfilmentCenter> getAllFulfilmentCenters() {
    return this.fulfilmentCenterRepository.findAll();
  }
}
