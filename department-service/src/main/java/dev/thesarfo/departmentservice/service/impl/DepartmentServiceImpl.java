package dev.thesarfo.departmentservice.service.impl;

import dev.thesarfo.departmentservice.dto.DepartmentDto;
import dev.thesarfo.departmentservice.entity.Department;
import dev.thesarfo.departmentservice.mapper.DepartmentMapper;
import dev.thesarfo.departmentservice.repository.DepartmentRepository;
import dev.thesarfo.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        Department department = DepartmentMapper.mapToDepartment(departmentDto);

        Department savedDepartment = departmentRepository.save(department);

        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);
        return DepartmentMapper.mapToDepartmentDto(department);
    }
}
