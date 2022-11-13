package com.example.demojson.repository;

import com.example.demojson.entity.ProductConnection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductConnectionRepository extends JpaRepository<ProductConnection, String> {
    @Query("""
    select
       p
    from 
       ProductConnection p fetch all properties 
    """

    )
    List<ProductConnection> findAllEagerLoad(Pageable page);
}
