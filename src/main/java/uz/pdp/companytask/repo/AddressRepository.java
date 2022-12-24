package com.example.lesson_1_task_1.repository;

import com.example.lesson_1_task_1.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
