package dev.thesarfo.employeeservice.service;

import dev.thesarfo.employeeservice.dto.APIResponseDto;
import dev.thesarfo.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    APIResponseDto getEmployeeById(Long employeeId);
}
