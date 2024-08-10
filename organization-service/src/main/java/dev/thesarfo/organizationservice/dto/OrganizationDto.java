package dev.thesarfo.organizationservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(
        description = "OrganizationDto Model Information"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDto {
    private Long id;

    @Schema(
            description = "this is the name of the organization"
    )
    private String organizationName;
    private String organizationDescription;

    @Schema(
            description = "this is the unique organization code"
    )
    private String organizationCode;
    private LocalDateTime organizationCreationDate;
}
