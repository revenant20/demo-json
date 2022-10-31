package com.example.demojson.repository;


import com.example.demojson.entity.AdditionalData;
import com.example.demojson.entity.AdditionalDataId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalDataRepository extends JpaRepository<AdditionalData, AdditionalDataId> {
}
