package com.example.demojson.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
@Entity
public class AdditionalData {
    @EmbeddedId
    AdditionalDataId additionalDataId;
    String value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AdditionalData that = (AdditionalData) o;
        return additionalDataId != null && Objects.equals(additionalDataId, that.additionalDataId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
