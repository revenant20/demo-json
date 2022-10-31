package com.example.demojson.repository;

import com.example.demojson.AbstractIntegrationTest;
import com.example.demojson.entity.AdditionalData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class AdditionalDataRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    AdditionalDataRepository repository;

    @Test
    void testDataGetting() {
        AdditionalData additionalData = repository.findById("name").orElseThrow(RuntimeException::new);
        assertEquals("name", additionalData.getName());
        assertEquals("string", additionalData.getType());
        assertEquals("important value", additionalData.getValue());
    }
}