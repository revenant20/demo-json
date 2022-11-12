package com.example.demojson.service;

import com.example.demojson.AbstractIntegrationTest;
import com.example.demojson.dto.AttributeDto;
import com.example.demojson.dto.NewProductDto;
import com.example.demojson.entity.Attribute;
import com.example.demojson.entity.Product;
import com.example.demojson.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductServiceImplTest extends AbstractIntegrationTest {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductRepository repository;

    @Transactional
    @Test
    void testSaving() {
        NewProductDto product = new NewProductDto();
        product.setName("unforgettable product");
        AttributeDto material = new AttributeDto();
        material.setAttrName("material");
        material.setAttrValue("metal");
        AttributeDto weight = new AttributeDto();
        weight.setAttrName("weight");
        weight.setAttrValue("15");
        String s = service.addProduct(product, List.of(material, weight));
        assertNotNull(s);

        Optional<Product> byId = repository.findById(s);
        Product prod = byId.orElseThrow();
        Map<String, Attribute> attributeMap = prod.getAttributes().stream().collect(Collectors.toMap(a -> a.getAttributeId().getAttrName(), Function.identity()));
        assertTrue(attributeMap.containsKey("weight"));
        assertTrue(attributeMap.containsKey("material"));
        assertEquals(2, attributeMap.size());
    }
}