package com.example.demojson.repository;

import com.example.demojson.AbstractIntegrationTest;
import com.example.demojson.entity.App;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    public AppRepository repository;

    @BeforeEach
    void setUp() {
        App entity = new App();
        entity.setNumber("Ban Gun");
        entity.setId("asdfg");
        repository.save(entity);
    }

    @Test
    public void testExisting() {
        var withRightJson = repository.getWithRightJson("b", "bc");
        assertEquals(2, withRightJson.size());
        Set<String> ids = withRightJson.stream()
                .map(App::getId)
                .collect(Collectors.toSet());
        Assertions.assertTrue(ids.contains("qwerty"));
        Assertions.assertTrue(ids.contains("qwerty2"));
    }

    @Test
    void testAuthorExists() {
        repository.findById("asdfg").ifPresentOrElse(post -> {
            assertEquals("Ban Gun", post.getNumber());
        }, () -> {
            throw new RuntimeException();
        });
    }

    @Transactional
    @Test
    void testCustomJson() {
        List<App> update = repository.findAppWithEventType("\"update\"");
        assertEquals(1, update.size());
        update.stream()
                .findFirst()
                .ifPresent(app -> {
                    assertEquals("75ff6bca-1267-4505-883a-4bad0e0e91b5", app.getAdditionalData().get("id").asText());
                });
    }

    @AfterEach
    void tearDown() {
        repository.deleteById("asdfg");
    }
}