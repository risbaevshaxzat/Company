package com.example.lesson_1_task_1.service;

import com.example.lesson_1_task_1.entity.Address;
import com.example.lesson_1_task_1.entity.Department;
import com.example.lesson_1_task_1.entity.Worker;
import com.example.lesson_1_task_1.payload.ApiResponse;
import com.example.lesson_1_task_1.payload.WorkerDto;
import com.example.lesson_1_task_1.repository.AddressRepository;
import com.example.lesson_1_task_1.repository.DepartmentRepository;
import com.example.lesson_1_task_1.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    public ApiResponse addWorker(WorkerDto workerDto) {

        boolean existsByPhoneNumber = workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber());
        if (existsByPhoneNumber) {
            return new ApiResponse("Such a Worker already exists", false);
        }

        Worker worker = new Worker();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());

        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        if (optionalAddress.isEmpty()) {
            return new ApiResponse("Address not found", false);
        }
        worker.setAddress(optionalAddress.get());

        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (optionalDepartment.isEmpty()) {
            return new ApiResponse("Department not found", false);
        }
        worker.setDepartment(optionalDepartment.get());

        workerRepository.save(worker);
        return new ApiResponse("Worker added", true);
    }

    public List<Worker> getWorkers() {

        List<Worker> workerList = workerRepository.findAll();
        return workerList;
    }

    public Worker getWorkerById(Integer id) {

        Optional<Worker> optionalWorker = workerRepository.findById(id);
        return optionalWorker.orElse(null);
    }

    public ApiResponse editWorker(Integer id, WorkerDto workerDto) {

        boolean existsByPhoneNumberAndIdNot = workerRepository.existsByPhoneNumberAndIdNot(workerDto.getPhoneNumber(), id);
        if (existsByPhoneNumberAndIdNot) {
            return new ApiResponse("Such a Worker with this phone number already exists", false);
        }
        Optional<Worker> optionalWorker =workerRepository.findById(id);
        if (optionalWorker.isEmpty()) {
            return new ApiResponse("Worker not found", false);
        }

        Worker editingWorker = new Worker();
        editingWorker.setName(workerDto.getName());
        editingWorker.setPhoneNumber(workerDto.getPhoneNumber());

        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        if (optionalAddress.isEmpty()) {
            return new ApiResponse("Address not found", false);
        }
        editingWorker.setAddress(optionalAddress.get());

        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (optionalDepartment.isEmpty()) {
            return new ApiResponse("Department not found", false);
        }
        editingWorker.setDepartment(optionalDepartment.get());

        workerRepository.save(editingWorker);
        return new ApiResponse("Worker edited", true);

    }

    public ApiResponse deleteWorker(Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isEmpty()) {
            return new ApiResponse("Worker not found", false);
        }
        workerRepository.deleteById(id);
        return new ApiResponse("Worker deleted", true);
    }

}
