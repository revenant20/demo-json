package com.example.demojson.repository;

import com.example.demojson.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, String> {
    Post getById(String id);

    @Query(nativeQuery = true, value = "select * from post where additional_data ->> :tag = :val")
    List<Post> getWithRightJson(@Param("tag") String tag, @Param("val") String val);

}
