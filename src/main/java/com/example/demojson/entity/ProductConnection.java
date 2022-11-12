package com.example.demojson.entity;


import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@TypeDef(
        typeClass = JsonBinaryType.class,
        defaultForType = ExternalIds.class
)
@Table(name = "product_connections")
public class ProductConnection {

    @Id
    private String productId;

    @Column(columnDefinition = "jsonb")
    private ExternalIds externalIds;

    // getters, setters
}
