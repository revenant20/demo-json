package com.example.demojson.repository;

import com.example.demojson.AbstractIntegrationTest;
import com.example.demojson.entity.AnotherProductConnection;
import com.example.demojson.entity.ProductConnection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class AnotherProductConnectionRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    AnotherProductConnectionRepository repository;

    @Test
    void testExists() {
        AnotherProductConnection productConnection = repository.findById("qwerty").orElseThrow();
        assertEquals("BEST_PHONE", productConnection.getExternalIds().get("HUTT_INC"));
        assertEquals("ULTRA_PHONE", productConnection.getExternalIds().get("TRADE_FED"));
    }
}