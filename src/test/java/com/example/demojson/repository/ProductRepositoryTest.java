package com.example.demojson.repository;

import com.example.demojson.AbstractIntegrationTest;
import com.example.demojson.entity.Attribute;
import com.example.demojson.entity.AttributeId;
import com.example.demojson.entity.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest extends AbstractIntegrationTest {

    public static final String ATTR_VAL = "attr-val";
    public static final String ATTR_NAME = "attr-name";
    @Autowired
    private ProductRepository repository;

    private final List<String> uuids = Stream.of(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID())
            .map(UUID::toString)
            .toList();

    @PostConstruct
    void beforeEach() {
        uuids.forEach(this::creatProduct);
    }


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
        List<Product> founded = repository.findAllByAttrValueAndAttrName(ATTR_VAL, ATTR_NAME);
        assertEquals(4, founded.size());
        String id = founded.stream().findFirst().orElseThrow().getId();
        assertTrue(uuids.contains(id));
        assertTrue(founded.iterator().next().getAttributes().size() > 0);
        System.out.println("testProductByAttrSearch over");
    }

    @Test
    void testProductByAttrSearchJoinFetch() {
        List<Product> founded = repository.findAllByAttrValueAndAttrNameJoinFetch(ATTR_VAL, ATTR_NAME);
        assertEquals(4, founded.size());
        String id = founded.stream().findFirst().orElseThrow().getId();
        assertTrue(uuids.contains(id));
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
        List<Product> founded = repository.findAllByAttrValueAndAttrNameJoinFetchPageable(ATTR_VAL, ATTR_NAME, Pageable.ofSize(1));
        assertEquals(1, founded.size());
        String id = founded.stream().findFirst().orElseThrow().getId();
        assertTrue(uuids.contains(id));
        assertTrue(founded.iterator().next().getAttributes().size() > 0);
    }

    @PreDestroy
    void tearDown() {
        uuids.forEach(repository::deleteById);
    }

    private void creatProduct(String uuid) {
        var entity = new Product();
        entity.setId(uuid);
        entity.setName("test-name-" + uuid);
        entity.setAttributes(new ArrayList<>());
        var attribute = new Attribute();
        var attributeId = new AttributeId();
        attributeId.setEntityId(entity);
        attributeId.setAttrName(ATTR_NAME);
        attribute.setAttrValue(ATTR_VAL);
        attribute.setAttributeId(attributeId);
        entity.getAttributes().add(attribute);
        repository.save(entity);
    }
}