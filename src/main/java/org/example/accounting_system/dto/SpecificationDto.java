package org.example.accounting_system.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpecificationDto {
    @NotBlank
    private String name;
    @Min(value = 0)
    private double sum;
    @NotBlank
    private long docNumber;
}
