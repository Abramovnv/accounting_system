package org.example.accounting_system.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.accounting_system.dto.DocumentDto;
import org.example.accounting_system.service.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/documents")
@Validated
@Slf4j
public class DocumentController {
    private final DocumentService documentService;

    @Operation(
            summary = "Создание документа",
            description = "первичное создание документа"
    )
    @PostMapping()
    public DocumentDto create(@RequestBody DocumentDto documentDto) {
        return documentService.create(documentDto);
    }

    @Operation(
            summary = "Поиск документа",
            description = "Поиск по номеру дукумента"
    )
    @GetMapping("/{docNumber}")
    public DocumentDto getDocument(@PathVariable long docNumber) {
        return documentService.getDocument(docNumber);
    }

    @Operation(
            summary = "Удаление документа",
            description = "Удаление по номеру документа"
    )
    @DeleteMapping("/{docNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long docNumber) {
        documentService.delete(docNumber);
    }

    @Operation(
            summary = "Изменение документа",
            description = "возможность изменения даты и описания документа"
    )
    @PutMapping("/{docNum}")
    public DocumentDto updateSpecification(@PathVariable long docNum,
                                           @RequestBody DocumentDto documentDto) {
        return documentService.updateDocument(docNum, documentDto);
    }


}
