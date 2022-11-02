package com.example.demojson.service;

import com.example.demojson.AbstractIntegrationTest;
import com.example.demojson.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class WriterPostServiceImplTest extends AbstractIntegrationTest {

    @Autowired
    private WriterPostService writerPostService;

    @Autowired
    private PostRepository repository;

    @Transactional
    @Test
    void testAuthorSaving() {
        writerPostService.updateAuthor("qwerty", "Bane");
        repository.findById("qwerty").ifPresentOrElse(post -> {
            assertEquals("Bane", post.getAuthor());
            assertEquals("zxc", post.getAdditionalData().get("str").asText());
        }, () -> {
            throw new RuntimeException();
        });
    }
}