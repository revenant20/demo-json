package com.example.demojson.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "agreements")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "agr_type")
public class Agreement {
    @Id
    @Column(name = "agreement_id")
    @EqualsAndHashCode.Include
    String id;

    @Column(name = "descr")
    String descr;

    @Column(name = "agr_type")
    String type;
}
