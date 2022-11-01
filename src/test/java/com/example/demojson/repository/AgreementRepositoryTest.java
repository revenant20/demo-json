package com.example.demojson.repository;

import com.example.demojson.AbstractIntegrationTest;
import com.example.demojson.entity.Agreement;
import com.example.demojson.entity.LoanAgreement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class AgreementRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private AgreementRepository repository;

    @Test
    void testDataExisting() {
        repository.findAll().forEach(System.out::println);
        LoanAgreement agreement = repository.findById("loanagr").orElseThrow();
        assertEquals(2, agreement.getAmount());
    }
}