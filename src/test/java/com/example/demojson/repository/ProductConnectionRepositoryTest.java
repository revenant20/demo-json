package com.example.demojson.repository;

import com.example.demojson.AbstractIntegrationTest;
import com.example.demojson.entity.ProductConnection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ProductConnectionRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private ProductConnectionRepository repository;

    @Test
    void testExists() {
        ProductConnection productConnection = repository.findById("qwerty").orElseThrow();
        assertEquals("SUPER_ZAIM_140", productConnection.getExternalIds().getHuttInc());
        assertEquals("MEGA_LOAN", productConnection.getExternalIds().getTradeFed());
    }
}