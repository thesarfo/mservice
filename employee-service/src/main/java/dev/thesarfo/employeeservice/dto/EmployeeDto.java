package dev.thesarfo.employeeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "EmployeeDto Model Information"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long id;

    private String firstName;
    private String lastName;

    @Schema(
            description = "This is the email of the employee"
    )
    private String email;

    @Schema(
            description = "This is the unique department code associated with the employee"
    )
    private String departmentCode;
    private String organizationCode;

}
