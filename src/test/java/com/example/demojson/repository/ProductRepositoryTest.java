package com.example.demojson.repository;

import com.example.demojson.AbstractIntegrationTest;
import com.example.demojson.entity.Attribute;
import com.example.demojson.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private ProductRepository repository;

    @Transactional
    @Test
    void testExists() {
        Product product = repository.findById("test").orElseThrow();
        List<Attribute> attributes = product.getAttributes();
        Attribute attribute = attributes.stream().findFirst().orElseThrow();
        assertEquals("test", attribute.getAttributeId().getEntityId().getId());
        assertEquals("name", attribute.getAttributeId().getEntityId().getName());
        assertEquals("important value", attribute.getAttrValue());
    }
}