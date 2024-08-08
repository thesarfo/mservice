package dev.thesarfo.organizationservice.service.impl;

import dev.thesarfo.organizationservice.dto.OrganizationDto;
import dev.thesarfo.organizationservice.entity.Organization;
import dev.thesarfo.organizationservice.mapper.OrganizationMapper;
import dev.thesarfo.organizationservice.repository.OrganizationRepository;
import dev.thesarfo.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);
        Organization savedOrganization = organizationRepository.save(organization);
        return OrganizationMapper.mapToOrganizationDto(savedOrganization);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        return OrganizationMapper.mapToOrganizationDto(organization);
    }
}
