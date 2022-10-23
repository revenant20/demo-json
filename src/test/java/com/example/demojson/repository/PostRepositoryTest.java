package com.example.demojson.repository;

import com.example.demojson.AbstractIntegrationTest;
import com.example.demojson.entity.Post;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

public class PostRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    public PostRepository repository;

    @Test
    public void testExisting() {
        var withRightJson = repository.getWithRightJson("b", "bc");
        Assertions.assertEquals(2, withRightJson.size());
        Set<String> ids = withRightJson.stream()
                .map(Post::getId)
                .collect(Collectors.toSet());
        Assertions.assertTrue(ids.contains("qwerty"));
        Assertions.assertTrue(ids.contains("qwerty2"));
    }
}