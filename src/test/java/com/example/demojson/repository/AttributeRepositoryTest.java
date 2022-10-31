package com.example.demojson.repository;

import com.example.demojson.AbstractIntegrationTest;
import com.example.demojson.entity.Attribute;
import com.example.demojson.entity.AttributeId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class AttributeRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    AttributeRepository repository;

    @Test
    void testDataGetting() {
        Attribute attribute = repository.findById(new AttributeId("test", "name"))
                .orElseThrow(RuntimeException::new);
        assertEquals("name", attribute.getAttributeId().getAttrName());
        assertEquals("important value", attribute.getAttrValue());
    }
}