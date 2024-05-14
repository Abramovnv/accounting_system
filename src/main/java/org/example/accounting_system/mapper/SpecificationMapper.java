package org.example.accounting_system.mapper;

import org.example.accounting_system.dto.SpecificationDto;
import org.example.accounting_system.entity.Specification;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface SpecificationMapper {
    Specification toEntity(SpecificationDto specificationDto);

    @Mapping(source = "document.docNumber", target = "docNumber")
    SpecificationDto toDto(Specification specification);

    void updateSpecificationFromDto(SpecificationDto specificationDto, @MappingTarget Specification specification);
}
