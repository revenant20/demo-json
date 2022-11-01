package com.example.demojson.repository;

import com.example.demojson.entity.Agreement;
import com.example.demojson.entity.LoanAgreement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AgreementRepository extends JpaRepository<LoanAgreement, String> {
}
