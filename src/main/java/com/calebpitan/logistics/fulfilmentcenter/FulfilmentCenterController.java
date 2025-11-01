package com.calebpitan.logistics.fulfilmentcenter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/fulfilment-centers")
@AllArgsConstructor
public class FulfilmentCenterController {

  private final FulfilmentCenterService fulfilmentCenterService;

  @GetMapping("/")
  public List<String> getFulfilmentCenters() {
    return Arrays.asList("Boston", "New York", "London");
  }
}
