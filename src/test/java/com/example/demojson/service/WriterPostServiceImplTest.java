package com.example.demojson.service;

import com.example.demojson.AbstractIntegrationTest;
import com.example.demojson.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class WriterPostServiceImplTest extends AbstractIntegrationTest {

    @Autowired
    private WriterPostService writerPostService;

    @Autowired
    private PostRepository repository;

    @Test
    void testAuthorSaving() {
        writerPostService.updateAuthor("qwerty", "Bane");
        repository.findById("qwerty").ifPresentOrElse(post -> {
            assertEquals("Bane", post.getAuthor());
        }, () -> {
            throw new RuntimeException();
        });
    }
}