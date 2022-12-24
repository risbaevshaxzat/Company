package com.example.lesson_1_task_1.repository;

import com.example.lesson_1_task_1.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
