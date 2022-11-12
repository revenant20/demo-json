package com.example.demojson.service;

import com.example.demojson.AbstractIntegrationTest;
import com.example.demojson.repository.AppRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional(propagation  = Propagation.NEVER)
@Slf4j
class WriterAppServiceImplTest extends AbstractIntegrationTest {

    @Autowired
    private UpdateAppService updateAppService;

    @Autowired
    private AppRepository repository;

    @Test
    void testAuthorSaving() {
        updateAppService.updateAuthor("qwerty", "Bane");
    }

    @Transactional
    public void getData() {
        repository.findById("qwerty").ifPresentOrElse(post -> {
            assertEquals("Bane", post.getNumber());
            assertEquals("zxc", post.getExternalData().get("str").asText());
        }, () -> {
            throw new RuntimeException();
        });
    }
}