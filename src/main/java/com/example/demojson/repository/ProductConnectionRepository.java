package com.example.demojson.repository;

import com.example.demojson.entity.ProductConnection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductConnectionRepository extends JpaRepository<ProductConnection, String> {
}
