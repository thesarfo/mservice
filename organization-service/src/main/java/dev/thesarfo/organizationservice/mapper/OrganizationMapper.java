package dev.thesarfo.organizationservice.mapper;

import dev.thesarfo.organizationservice.dto.OrganizationDto;
import dev.thesarfo.organizationservice.entity.Organization;

public class OrganizationMapper {
    public static OrganizationDto mapToOrganizationDto(Organization organization) {
        return new OrganizationDto(
                organization.getId(),
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrganizationCode(),
                organization.getOrganizationCreationDate()
        );
    }

    public static Organization mapToOrganization(OrganizationDto organizationDto) {
        return new Organization(
                organizationDto.getId(),
                organizationDto.getOrganizationName(),
                organizationDto.getOrganizationDescription(),
                organizationDto.getOrganizationCode(),
                organizationDto.getOrganizationCreationDate()
        );
    }
}
