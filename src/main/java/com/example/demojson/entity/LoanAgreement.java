package com.example.demojson.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "loan_agreements")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@DiscriminatorValue(value = "LOAN")
public class LoanAgreement extends Agreement{
    @Column(name = "length")
    int length;

    @Column(name = "amount")
    int amount;
}
