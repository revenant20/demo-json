package com.example.demojson.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "hire_agreements")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@DiscriminatorValue(value = "HIRE")
public class HireAgreement extends Agreement{
    @Column(name = "employee_passport")
    String employeePassport;

    @Column(name = "employer_passport")
    String employerPassport;
}
