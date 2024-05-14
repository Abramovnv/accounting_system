package org.example.accounting_system.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.accounting_system.dto.SpecificationDto;
import org.example.accounting_system.entity.Document;
import org.example.accounting_system.entity.Specification;
import org.example.accounting_system.mapper.SpecificationMapper;
import org.example.accounting_system.repository.DocumentRepository;
import org.example.accounting_system.repository.SpecificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SpecificationService {
    private final SpecificationRepository specificationRepository;
    private final DocumentRepository documentRepository;
    private final SpecificationMapper specificationMapper;

    @Transactional
    public SpecificationDto create(SpecificationDto specificationDto) {
        Document document = documentRepository.findDocumentByDocNumber(specificationDto.getDocNumber())
                .orElseThrow(() -> new RuntimeException("Document with № " + specificationDto.getDocNumber() + " is not exists"));
        Specification specification = specificationMapper.toEntity(specificationDto);
        specification.setDocument(document);
        document.getSpecifications().add(specification);
        document.setSum(document.getSum() + specification.getSum());
        return specificationMapper.toDto(specificationRepository.save(specification));
    }

    @Transactional(readOnly = true)
    public SpecificationDto getSpecification(long specificationId) {
        Specification specification = checkExistenceSpecification(specificationId);
        return specificationMapper.toDto(specification);
    }

    @Transactional
    public void deleteSpecification(long specificationId) {
        Specification specification = checkExistenceSpecification(specificationId);
        Document document = documentRepository.findDocumentByDocNumber(specification.getDocument().getDocNumber())
                .orElseThrow(() -> new RuntimeException("Документа не найдено"));
        document.setSum(document.getSum() - specification.getSum());
        specificationRepository.deleteById(specificationId);
    }

    @Transactional
    public SpecificationDto updateSpecification(long specificationId, SpecificationDto specificationDto) {
        Specification specification = checkExistenceSpecification(specificationId);
        Document currentDocument = specification.getDocument();
        if (specification.getDocument().getDocNumber() != specificationDto.getDocNumber()) {
            Document newDocument = documentRepository.findDocumentByDocNumber(specificationDto.getDocNumber()).
                    orElseThrow(() -> new RuntimeException("Document with № " + specificationDto.getDocNumber() + " is not exists"));
            currentDocument.setSum(currentDocument.getSum() - specification.getSum());
            currentDocument.getSpecifications().removeIf(s -> s.getId() == specificationId);
            specificationMapper.updateSpecificationFromDto(specificationDto, specification);
            specification.setDocument(newDocument);
            newDocument.getSpecifications().add(specification);
            newDocument.setSum(currentDocument.getSum() + specificationDto.getSum());
            documentRepository.save(newDocument);
        } else if (specification.getSum() != specificationDto.getSum()) {
            currentDocument.setSum(currentDocument.getSum() - specification.getSum() + specificationDto.getSum());
            specificationMapper.updateSpecificationFromDto(specificationDto, specification);
            documentRepository.save(currentDocument);
        } else {
            specificationMapper.updateSpecificationFromDto(specificationDto, specification);
        }
        return specificationMapper.toDto(specification);
    }

    private Specification checkExistenceSpecification(long specification) {
        return specificationRepository.findById(specification)
                .orElseThrow(() -> new EntityNotFoundException("Specification with № " + specification + " doesn't exist"));
    }
}
