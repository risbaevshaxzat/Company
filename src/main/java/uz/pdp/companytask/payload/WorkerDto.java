package com.example.lesson_1_task_1.payload;

import lombok.Data;

@Data
public class WorkerDto {

    private String name;
    private String phoneNumber;
    private Integer addressId;
    private Integer departmentId;
}
