package com.example.demojson.entity;


import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@TypeDef(
        typeClass = JsonBinaryType.class,
        defaultForType = InternalProductId.class
)
public class ProductConnection {

    @Id
    private String productId;

    @Column(columnDefinition = "jsonb")
    private InternalProductId internalProductId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductConnection that = (ProductConnection) o;
        return productId != null && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
