package com.example.demojson.repository;

import com.example.demojson.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {
    Post getById(String id);
}
