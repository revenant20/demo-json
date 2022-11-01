package com.example.demojson.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class AttributeId implements Serializable {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "entity_id")
    Product entityId;

    String attrName;

    public AttributeId() {
    }

    public AttributeId(Product entityId, String attrName) {
        this.entityId = entityId;
        this.attrName = attrName;
    }

    @Override
    public String toString() {
        return "AttributeId{" +
                "entityId=" + entityId.getId() +
                ", attrName='" + attrName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttributeId that = (AttributeId) o;

        if (!Objects.equals(entityId, that.entityId)) return false;
        return Objects.equals(attrName, that.attrName);
    }

    @Override
    public int hashCode() {
        int result = entityId != null ? entityId.hashCode() : 0;
        result = 31 * result + (attrName != null ? attrName.hashCode() : 0);
        return result;
    }


}
