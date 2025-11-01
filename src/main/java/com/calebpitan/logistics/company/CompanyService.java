package com.calebpitan.logistics.company;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyService {
  private final CompanyRepository companyRepository;

  public Company createCompany(String name) {
    Company company = new Company();
    company.setName(name);

    return companyRepository.save(company);
  }

  public List<Company> getAllCompanies() {
    return companyRepository.findAll();
  }

  public Optional<Company> getCompanyById(Long id) {
    return companyRepository.findById(id);
  }

  public Optional<Company> getCompanyByName(String name) {
    return companyRepository.findByName(name);
  }
}
