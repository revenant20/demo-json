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
        assertEquals("SUPER_ZAIM_140", productConnection.getExternalIds().get("HUTT_INC"));
        assertEquals("MEGA_LOAN", productConnection.getExternalIds().get("TRADE_FED"));
        assertEquals("death_star", productConnection.getExternalIds().get("EMPIRE"));
    }
}