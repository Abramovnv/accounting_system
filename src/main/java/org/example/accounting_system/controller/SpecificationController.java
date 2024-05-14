package org.example.accounting_system.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.accounting_system.dto.SpecificationDto;
import org.example.accounting_system.service.SpecificationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/specifications")
@Validated
@Slf4j
public class SpecificationController {
    private final SpecificationService specificationService;

    @Operation(
            summary = "Создание спецификации",
            description = "первичное создание спецификации"
    )
    @PostMapping()
    public SpecificationDto create(@RequestBody SpecificationDto specificationDto) {
        return specificationService.create(specificationDto);
    }

    @Operation(
            summary = "Поиск спецификации",
            description = "Поиск спецификации по № id"
    )
    @GetMapping("/{specificationId}")
    public SpecificationDto getSpecification(@PathVariable long specificationId) {
        return specificationService.getSpecification(specificationId);
    }

    @Operation(
            summary = "Удаление спецификации",
            description = "Удаление спецификации по № id"
    )
    @DeleteMapping("/{specificationId}")
    public void deleteSpecification(@PathVariable long specificationId) {
        specificationService.deleteSpecification(specificationId);
    }

    @Operation(
            summary = "Изменение спецификации",
            description = "Изменеие спецификации"
    )
    @PutMapping("/{specificationId}")
    public SpecificationDto updateSpecification(@PathVariable long specificationId,
                                                @RequestBody SpecificationDto specificationDto) {
        return specificationService.updateSpecification(specificationId, specificationDto);
    }
}
