package org.example.accounting_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "document")
public class Document {
    @Id
    @Column(name = "doc_number", unique = true)
    private Long docNumber;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "sum")
    private Double sum;

    @Column(name = "description", length = 256)
    private String description;

    @OneToMany(mappedBy = "document")
    List<Specification> specifications;
}
