package com.example.lesson_1_task_1.service;

import com.example.lesson_1_task_1.entity.Company;
import com.example.lesson_1_task_1.entity.Department;
import com.example.lesson_1_task_1.payload.ApiResponse;
import com.example.lesson_1_task_1.payload.DepartmentDto;
import com.example.lesson_1_task_1.repository.CompanyRepository;
import com.example.lesson_1_task_1.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CompanyRepository companyRepository;

    public ApiResponse addDepartment(DepartmentDto departmentDto) {

        Department department = new Department();
        department.setName(departmentDto.getName());

        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (optionalCompany.isEmpty()) {
            return new ApiResponse("Company not found",false);
        }
        department.setCompany(optionalCompany.get());

        departmentRepository.save(department);
        return new ApiResponse("Department added", true);
    }

    public List<Department> getDepartments() {

        List<Department> departments = departmentRepository.findAll();
        return departments;
    }

    public Department getDepartmentById(Integer id) {

        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment.orElse(null);
    }

    public ApiResponse editDepartment(Integer id, DepartmentDto departmentDto) {

        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty()) {
            return new ApiResponse("Department not fount", false);
        }
        Department editingDepartment = optionalDepartment.get();
        editingDepartment.setName(departmentDto.getName());

        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (optionalCompany.isEmpty()) {
            return new ApiResponse("Company not found",false);
        }
        editingDepartment.setCompany(optionalCompany.get());

        departmentRepository.save(editingDepartment);
        return new ApiResponse("Department edited", true);
    }

    public ApiResponse deleteDepartment(Integer id) {

        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty()) {
            return new ApiResponse("Department not fount", false);
        }
        departmentRepository.deleteById(id);
        return new ApiResponse("Department deleted", true);
    }
}
