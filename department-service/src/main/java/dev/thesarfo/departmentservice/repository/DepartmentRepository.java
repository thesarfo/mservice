package dev.thesarfo.departmentservice.repository;

import dev.thesarfo.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
