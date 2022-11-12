package com.example.demojson.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

@Data
@Entity
@TypeDef(
        typeClass = JsonBinaryType.class,
        defaultForType = JsonNode.class
)
public class App {

    @Id
    String id;

    String number;

    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "jsonb")
    JsonNode externalData;

   //getters, setters
}
