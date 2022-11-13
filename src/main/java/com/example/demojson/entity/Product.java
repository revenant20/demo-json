package com.example.demojson.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "products")
public class Product {

    @Id
    private String id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL
            , fetch = FetchType.LAZY
            , mappedBy = "attributeId.entityId"
            , orphanRemoval = true
    )
    @ToString.Exclude
    private List<Attribute> attributes;

    // getters, setters
}
