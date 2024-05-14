package org.example.accounting_system.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.accounting_system.dto.DocumentDto;
import org.example.accounting_system.entity.Document;
import org.example.accounting_system.mapper.DocumentMapper;
import org.example.accounting_system.repository.DocumentRepository;
import org.example.accounting_system.repository.SpecificationRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final SpecificationRepository specificationRepository;
    private final DocumentMapper documentMapper;

    @Transactional
    public DocumentDto create(DocumentDto documentDto) {
        Document newDocument;
        try {
            Document document = documentMapper.toEntity(documentDto);
            newDocument = documentRepository.create(document.getDocNumber(),
                    document.getDate(), document.getDescription());
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityViolationException("Document with № " + documentDto.getDocNumber() + " already exists");
        }
        return documentMapper.toDto(newDocument);
    }

    @Transactional(readOnly = true)
    public DocumentDto getDocument(long docNumber) {
        return documentMapper.toDto(checkExistenceDocument(docNumber));
    }

    @Transactional
    public void delete(long docNumber) {
        Document document = checkExistenceDocument(docNumber);
        specificationRepository.deleteAll(document.getSpecifications());
        documentRepository.delete(document);
    }

    @Transactional()
    public DocumentDto updateDocument(long docNum, DocumentDto documentDto) {
        Document document = checkExistenceDocument(docNum);
        var checkNumber = document.getDocNumber().equals(documentDto.getDocNumber());
        if (!checkNumber) {
            throw new RuntimeException("You can't change document number");
        } else {
            documentMapper.updateDocumentFromDto(documentDto, document);
        }
        return documentMapper.toDto(document);
    }

    private Document checkExistenceDocument(long docNumber) {
        return documentRepository.findById(docNumber)
                .orElseThrow(() -> new EntityNotFoundException("Document with № " + docNumber + " doesn't exist"));
    }
}
