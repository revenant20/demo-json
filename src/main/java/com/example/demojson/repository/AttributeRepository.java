package com.example.demojson.repository;


import com.example.demojson.entity.Attribute;
import com.example.demojson.entity.AttributeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute, AttributeId> {
}
