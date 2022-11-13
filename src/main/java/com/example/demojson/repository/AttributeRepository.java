package com.example.demojson.repository;


import com.example.demojson.entity.Attribute;
import com.example.demojson.entity.AttributeId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute, AttributeId> {

    Page<Attribute> findAllByAttrValue(String attrValue, Pageable pageable);

}
