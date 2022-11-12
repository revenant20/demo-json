package com.example.demojson.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        App app = (App) o;
        return id != null && Objects.equals(id, app.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

   //getters, setters
}
