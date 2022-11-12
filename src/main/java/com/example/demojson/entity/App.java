package com.example.demojson.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

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
//    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    JsonNode externalData;

   //getters, setters
}
