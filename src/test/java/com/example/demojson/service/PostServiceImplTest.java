package com.example.demojson.service;

import com.example.demojson.AbstractIntegrationTest;
import com.example.demojson.entity.Post;
import com.example.demojson.repository.PostRepository;
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
class PostServiceImplTest extends AbstractIntegrationTest {

    @Autowired
    public PostService postService;

    @Autowired
    public PostRepository repository;

    @BeforeEach
    void setUp() {
        Post post = new Post();
        post.setId("as");
        post.setAdditionalData("""
                {
                  "a": "asd"
                }""");
        repository.save(post);
    }

    @Transactional(propagation = Propagation.NEVER)
    @Test
    void testJsonSaving() {
        assertThrows(LazyInitializationException.class, () -> {
            var postDto = postService.getPostByReferenceId("as");
            assertEquals("as", postDto.getId());
            assertEquals("""
                    {"a": "asd"}""", postDto.getJson());
            log.info("{}", postDto);
        });
    }

    @Transactional(propagation = Propagation.NEVER)
    @Test
    void testJsonSaving1() {
        assertDoesNotThrow(() -> {
            var postDto = postService.getPostById("as");
            assertEquals("as", postDto.getId());
            assertEquals("""
                    {"a": "asd"}""", postDto.getJson());
            log.info("{}", postDto);
        });
    }

    @Transactional(propagation = Propagation.NEVER)
    @Test
    void testJsonSaving2() {
        assertThrows(LazyInitializationException.class, () -> {
            var postDto = postService.getPostByIdWithoutOptional("as");
            assertEquals("as", postDto.getId());
            assertEquals("""
                    {"a": "asd"}""", postDto.getJson());
            log.info("{}", postDto);
        });
    }

    @Transactional(propagation = Propagation.NEVER)
    @Test
    void testJsonSaving3() {
        assertThrows(LazyInitializationException.class, () -> {
            var postDto = postService.getPostByCustomMethode("as");
            assertEquals("as", postDto.getId());
            assertEquals("""
                    {"a": "asd"}""", postDto.getJson());
            log.info("{}", postDto);
        });
    }

    @Test
    void testJson() {
        assertThrows(LazyInitializationException.class, () -> {
            var postDto = postService.getPostByReferenceId("as");
            assertEquals("as", postDto.getId());
            assertEquals("""
                    {"a": "asd"}""", postDto.getJson());
            log.info("{}", postDto);
        });
    }
}