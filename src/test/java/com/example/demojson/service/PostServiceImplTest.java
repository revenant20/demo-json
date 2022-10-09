package com.example.demojson.service;

import com.example.demojson.entity.Post;
import com.example.demojson.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;
import static org.testcontainers.shaded.org.hamcrest.MatcherAssert.*;


@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Testcontainers
class PostServiceImplTest {

    @Container
    private static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer("postgres")
            .withDatabaseName("foo")
            .withUsername("foo")
            .withPassword("secret")     ;

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

    @Test
    void test() {
        assertThat("", postgresqlContainer.isRunning());
        Post post = new Post();
        post.setId("as");
        repository.save(post);
        assertDoesNotThrow(() -> postService.getPost("as"));
    }
}