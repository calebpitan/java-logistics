package com.calebpitan.logistics.company;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/companies")
@AllArgsConstructor
public class CompanyController {

  private final CompanyService companyService;

  @GetMapping("/")
  public List<Company> getCompanies() {
    return this.companyService.getAllCompanies();
  }

  @GetMapping("/{id}")
  public Company getCompanyById(@PathVariable("id") Long id) {
    return this.companyService
        .getCompanyById(id)
        .orElseThrow(() -> new IllegalArgumentException("Company not found"));
  }
}
