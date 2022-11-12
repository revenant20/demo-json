package com.example.demojson.repository;

import com.example.demojson.entity.Attribute;
import com.example.demojson.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findByAttributesContaining(Attribute attribute);
}
