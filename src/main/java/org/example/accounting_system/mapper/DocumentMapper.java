package org.example.accounting_system.mapper;

import org.example.accounting_system.dto.DocumentDto;
import org.example.accounting_system.entity.Document;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DocumentMapper {
    Document toEntity(DocumentDto documentDto);

    DocumentDto toDto(Document document);

    void updateDocumentFromDto(DocumentDto documentDto, @MappingTarget Document document);
}
