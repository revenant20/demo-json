package com.example.demojson.repository;

import com.example.demojson.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, String> {
    Post getById(String id);

    @Query(nativeQuery = true, value = "select * from post where some_data ->> :teg = :val")
    List<Post> getWithRightJson(@Param("teg") String teg, @Param("val") String val);

}
