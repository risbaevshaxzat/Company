package com.example.lesson_1_task_1.service;

import com.example.lesson_1_task_1.entity.Address;
import com.example.lesson_1_task_1.entity.Company;
import com.example.lesson_1_task_1.payload.ApiResponse;
import com.example.lesson_1_task_1.payload.CompanyDto;
import com.example.lesson_1_task_1.repository.AddressRepository;
import com.example.lesson_1_task_1.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;

    public ApiResponse addCompany(CompanyDto companyDto) {

        Company company = new Company();
        company.setCorpNmae(companyDto.getCorpNmae());
        company.setDirectorName(companyDto.getDirectorName());

        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (optionalAddress.isEmpty()) {
            return new ApiResponse("Address not found",false);
        }
        company.setAddress(optionalAddress.get());

        companyRepository.save(company);
        return new ApiResponse("Company added", true);
    }

    public List<Company> getCompanies() {

        List<Company> companies = companyRepository.findAll();
        return companies;
    }

    public Company getCompanyById(Integer id) {

        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.orElse(null);
    }

    public ApiResponse editCompany(Integer id, CompanyDto companyDto) {

        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isEmpty()) {
            return new ApiResponse("Company not fount", false);
        }
        Company editingCompany = optionalCompany.get();
        editingCompany.setCorpNmae(companyDto.getCorpNmae());
        editingCompany.setDirectorName(companyDto.getDirectorName());

        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (optionalAddress.isEmpty()) {
            return new ApiResponse("Address not found",false);
        }
        editingCompany.setAddress(optionalAddress.get());

        companyRepository.save(editingCompany);
        return new ApiResponse("Company edited", true);
    }

    public ApiResponse deleteCompany(Integer id) {

        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isEmpty()) {
            return new ApiResponse("Company not fount", false);
        }
        companyRepository.deleteById(id);
        return new ApiResponse("Company deleted", true);
    }
}
