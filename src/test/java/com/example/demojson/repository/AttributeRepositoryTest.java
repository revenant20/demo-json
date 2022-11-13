package com.example.demojson.repository;

import com.example.demojson.AbstractIntegrationTest;
import com.example.demojson.entity.Attribute;
import com.example.demojson.entity.AttributeId;
import com.example.demojson.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AttributeRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    AttributeRepository repository;

    @Autowired
    ProductRepository productRepository;

    @Test
    void testDataGetting() {
        Product product = new Product();
        product.setId("test1");
        product.setName("test1");
        List<Attribute> list = new ArrayList<>();
        Attribute attr = new Attribute();
        attr.setAttributeId(new AttributeId(product, "name"));
        attr.setAttrValue("val");
        list.add(attr);
        product.setAttributes(list);
        productRepository.save(product);

        Attribute attribute = repository.findById(new AttributeId(product, "name"))
                .orElseThrow(RuntimeException::new);
        assertEquals("name", attribute.getAttributeId().getAttrName());
        assertEquals("val", attribute.getAttrValue());
    }

    @Test
    void testPageable() {
        Page<Attribute> allByAttrValue = repository.findAllByAttrValue("3", PageRequest.of(1, 2));
        assertEquals(2, allByAttrValue.stream().count());
        assertEquals(3, allByAttrValue.getTotalPages());
    }
}