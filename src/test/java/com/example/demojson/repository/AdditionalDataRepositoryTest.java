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
        AdditionalData additionalData = repository.findById(1).orElseThrow(RuntimeException::new);
        assertEquals(1, additionalData.getId());
        assertEquals("string", additionalData.getType());
        assertEquals("important value", additionalData.getValue());
    }
}