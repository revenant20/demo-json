package com.example.demojson.service;

import com.example.demojson.AbstractIntegrationTest;
import com.example.demojson.entity.App;
import com.example.demojson.repository.AppRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladmihalcea.hibernate.type.json.internal.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@Slf4j
class AppServiceImplTest extends AbstractIntegrationTest {

    @Autowired
    public AppService appService;

    @Autowired
    public AppRepository repository;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        App app = new App();
        app.setId("as");
        app.setExternalData(JacksonUtil.toJsonNode("""
                {
                  "a": "asd"
                }"""));
        repository.save(app);
    }

    @Transactional(propagation = Propagation.NEVER)
    @Test
    void testJsonSaving() {
        assertThrows(LazyInitializationException.class, () -> {
            var appDto = appService.getAppByReferenceId("as");
            assertEquals("as", appDto.getId());
            assertEquals("""
                    {"a": "asd"}""", appDto.getJson());
            log.info("{}", appDto);
        });
    }

    @Transactional(propagation = Propagation.NEVER)
    @Test
    void testJsonSaving1() {
        assertDoesNotThrow(() -> {
            var appDto = appService.getAppById("as");
            assertEquals("as", appDto.getId());
            assertEquals("""
                    {"a":"asd"}""", appDto.getJson());
            log.info("{}", appDto);
        });
    }

    @Transactional(propagation = Propagation.NEVER)
    @Test
    void testJsonSaving2() {
        assertThrows(LazyInitializationException.class, () -> {
            var appDto = appService.getAppByIdWithoutOptional("as");
            assertEquals("as", appDto.getId());
            assertEquals("""
                    {"a":"asd"}""", appDto.getJson());
            log.info("{}", appDto);
        });
    }

    @Transactional(propagation = Propagation.NEVER)
    @Test
    void testJsonSaving3() {
        assertThrows(LazyInitializationException.class, () -> {
            var appDto = appService.getAppByCustomMethode("as");
            assertEquals("as", appDto.getId());
            assertEquals("""
                    {"a": "asd"}""", appDto.getJson());
            log.info("{}", appDto);
        });
    }

    @Test
    void testJson() {
        assertThrows(LazyInitializationException.class, () -> {
            var appDto = appService.getAppByReferenceId("as");
            assertEquals("as", appDto.getId());
            assertEquals("""
                    {"a": "asd"}""", appDto.getJson());
            log.info("{}", appDto);
        });
    }
}