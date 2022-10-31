package com.example.demojson.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Attribute {
    @EmbeddedId
    AttributeId attributeId;
    String attrValue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Attribute that = (Attribute) o;
        return attributeId != null && Objects.equals(attributeId, that.attributeId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
