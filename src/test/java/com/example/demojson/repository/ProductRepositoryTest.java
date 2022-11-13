package com.example.demojson.repository;

import com.example.demojson.AbstractIntegrationTest;
import com.example.demojson.entity.Attribute;
import com.example.demojson.entity.AttributeId;
import com.example.demojson.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductRepositoryTest extends AbstractIntegrationTest {

    public static final String ATTR_VAL = "attr-val";
    public static final String ATTR_NAME = "attr-name";
    @Autowired
    private ProductRepository repository;

    private static final List<String> uuids = Stream.of(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID())
            .map(UUID::toString)
            .toList();

    @PostConstruct
    void beforeEach() {
        if (!repository.existsById(uuids.stream().findFirst().orElseThrow())) {
            uuids.forEach(this::creatProduct);
        }
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
        List<Product> found = repository.findAllByAttrValueAndAttrName(ATTR_VAL, ATTR_NAME);
        assertEquals(4, found.size());
        String id = found.stream().findFirst().orElseThrow().getId();
        assertTrue(uuids.contains(id));
        assertTrue(found.iterator().next().getAttributes().size() > 0);
    }

    @Test
    void testProductByAttrSearchJoinFetch() {
        List<Product> found = repository.findAllByAttrValueAndAttrNameJoinFetch(ATTR_VAL, ATTR_NAME);
        assertEquals(4, found.size());
        String id = found.stream().findFirst().orElseThrow().getId();
        assertTrue(uuids.contains(id));
        assertTrue(found.iterator().next().getAttributes().size() > 0);
        found.stream()
                .map(Product::getAttributes)
                .flatMap(Collection::stream)
                .forEach(System.out::println);
    }

    /**
     * Pay attention, that there's no limit clause in sql.
     * That is because of eagerly loaded attributes.
     * Don't do this at home )))
     */
    @Test
    @Transactional
    void testProductByAttrSearchJoinFetchPageable() {
        List<Product> found = repository.findAllByAttrValueAndAttrNameJoinFetchPageable(ATTR_VAL, ATTR_NAME, Pageable.ofSize(1));
        assertEquals(1, found.size());
        String id = found.stream().findFirst().orElseThrow().getId();
        assertTrue(uuids.contains(id));
        assertTrue(found.iterator().next().getAttributes().size() > 0);
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
        createAttr(entity);
        repository.save(entity);
    }

    private static void createAttr(Product entity) {
        for (int i = 0; i < 2; i++) {
            var attribute = new Attribute();
            var attributeId = new AttributeId();
            attributeId.setEntityId(entity);
            attributeId.setAttrName(ATTR_NAME + i);
            attribute.setAttrValue(ATTR_VAL + i);
            attribute.setAttributeId(attributeId);
            entity.getAttributes().add(attribute);
        }
    }
}