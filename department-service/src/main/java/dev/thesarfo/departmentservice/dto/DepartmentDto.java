package dev.thesarfo.departmentservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "DepartmentDto Model Information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long id;

    @Schema(
            description = "this is the name of the department"
    )
    private String departmentName;
    @Schema(
            description = "this is the description of the department"
    )
    private String departmentDescription;
    @Schema(
            description = "this is the unique department code"
    )
    private String departmentCode;
}
