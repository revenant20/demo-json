package com.example.demojson.repository;

import com.example.demojson.AbstractIntegrationTest;
import com.example.demojson.entity.Attribute;
import com.example.demojson.entity.AttributeId;
import com.example.demojson.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Test
    void testContaining() {
        Attribute attribute = new Attribute();
        Product product1 = new Product();
        product1.setId("test");
        attribute.setAttributeId(new AttributeId(product1, "name"));
        attribute.setAttrValue("important value");

        Optional<Product> byAttributesContaining = repository.findByAttributesContaining(attribute);
        Product product = byAttributesContaining.orElseThrow();
        assertEquals("test", product.getId());
    }

    @Test
    @Transactional
    void testProductByAttrSearch() {
        List<Product> founded = repository.findAllByAttrValueAndAttrName("3", "vcost");
        assertEquals(1, founded.size());
        assertEquals("for_test", founded.stream().findFirst().orElseThrow().getId());
        assertTrue(founded.iterator().next().getAttributes().size() > 0);
    }

    @Test
    void testProductByAttrSearchJoinFetch() {
        List<Product> founded = repository.findAllByAttrValueAndAttrNameJoinFetch("3", "vcost");
        assertEquals(1, founded.size());
        assertEquals("for_test", founded.stream().findFirst().orElseThrow().getId());
        assertTrue(founded.iterator().next().getAttributes().size() > 0);
    }

    /**
     * Pay attention, that there's no limit clause in sql.
     * That is because of eagerly loaded attributes.
     * Don't do this at home )))
     */
    @Test
    @Transactional
    void testProductByAttrSearchJoinFetchPageable() {
        List<Product> founded = repository.findAllByAttrValueAndAttrNameJoinFetchPageable("3", "vcost", Pageable.ofSize(1));
        assertEquals(1, founded.size());
        assertEquals("for_test", founded.stream().findFirst().orElseThrow().getId());
        assertTrue(founded.iterator().next().getAttributes().size() > 0);
    }
}