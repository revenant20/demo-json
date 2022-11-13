package com.example.demojson.repository;

import com.example.demojson.AbstractIntegrationTest;
import com.example.demojson.entity.ProductConnection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductConnectionRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private ProductConnectionRepository repository;

    @Test
    @Transactional
    void testExists() {
        ProductConnection productConnection = repository.findById("qwerty").orElseThrow();
        assertEquals("SUPER_ZAIM_140", productConnection.getExternalIds().getHuttInc());
        assertEquals("MEGA_LOAN", productConnection.getExternalIds().getTradeFed());
    }

    @Test
    /**
     * Limit clause is present in query, even though we load
     * json eagerly. If we had used EAV with eager load,
     * Hibernate would've loaded all pages and applied limit
     * locally.
     */
    void findAllEagerLoad() {
        final List<ProductConnection> all = repository.findAllEagerLoad(Pageable.ofSize(10));
    }
}