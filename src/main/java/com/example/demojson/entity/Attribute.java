package com.example.demojson.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

@Data
@Entity
public class Attribute {
    @EmbeddedId
    AttributeId attributeId;
    String attrValue;

    //getters, setters
}
