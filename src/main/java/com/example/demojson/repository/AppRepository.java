package com.example.demojson.repository;

import com.example.demojson.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppRepository extends JpaRepository<App, String> {
    App getById(String id);

    @Query(nativeQuery = true, value = "select * from app where additional_data ->> :tag = :val")
    List<App> getWithRightJson(@Param("tag") String tag, @Param("val") String val);

}
