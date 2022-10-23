package com.example.demojson.service;

import com.example.demojson.entity.Post;
import com.example.demojson.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testcontainers.shaded.org.hamcrest.MatcherAssert.assertThat;


@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Testcontainers
class PostServiceImplTest {

    @Container
    private static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer("postgres")
            .withDatabaseName("foo")
            .withUsername("foo")
            .withPassword("secret");

    @DynamicPropertySource
    static void dataSourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresqlContainer::getUsername);
        registry.add("spring.datasource.password", postgresqlContainer::getPassword);
    }

    @Autowired
    public PostService postService;

    @Autowired
    public PostRepository repository;

    @BeforeEach
    void setUp() {
        assertThat("", postgresqlContainer.isRunning());
        Post post = new Post();
        post.setId("as");
        post.setSomeData("""
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
        assertThrows(LazyInitializationException.class, () -> {
            var postDto = postService.getPostById("as");
            assertEquals("as", postDto.getId());
            assertEquals("""
                    {
                    "a": "asd"
                    }""", postDto.getJson());
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