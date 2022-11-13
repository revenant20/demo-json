package com.example.demojson.repository;

import com.example.demojson.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppRepository extends JpaRepository<App, String> {
    App getById(String id);

    @Query(nativeQuery = true, value = "select * from apps where external_data ->> :tag = :val")
    List<App> getWithRightJson(@Param("tag") String tag, @Param("val") String val);

    @Query(nativeQuery = true,
//            value = "select * from app where additional_data #> '{event,type}' = :type \\:\\:jsonb")
            value = "select * from apps where external_data #> '{event,type}' = cast(:type as jsonb)")
//            value = "select * from app where additional_data #> '{event,type}' = :type")
//    @Query(nativeQuery = true, value = "select * from app where additional_data #> '{event,type}' = '\"update\"'")
    List<App> findAppWithEventType(@Param("type") String type);

    @Query(value = """
            select * from apps where external_data @> :json \\:\\:jsonb
            """,
    nativeQuery = true)
    List<App> findAllWithWriteJson(@Param("json") String json);

}
