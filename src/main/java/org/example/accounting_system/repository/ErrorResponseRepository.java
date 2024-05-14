package org.example.accounting_system.repository;

import org.example.accounting_system.entity.ErrorResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorResponseRepository extends JpaRepository<ErrorResponse, Long> {
}
