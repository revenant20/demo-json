package com.example.demojson.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Map;
import java.util.Objects;

@Data
@Entity
@TypeDef(
        typeClass = JsonBinaryType.class,
        defaultForType = Map.class
)
@Table(name = "product_connections")
public class AnotherProductConnection {

    @Id
    private String productId;

    @Column(columnDefinition = "jsonb")
    private Map<String, String> externalIds;

    // getters, setters
}
