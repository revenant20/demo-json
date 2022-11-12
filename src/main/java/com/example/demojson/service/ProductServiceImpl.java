package com.example.demojson.service;

import com.example.demojson.dto.AttributeDto;
import com.example.demojson.dto.NewProductDto;
import com.example.demojson.entity.Attribute;
import com.example.demojson.entity.AttributeId;
import com.example.demojson.entity.Product;
import com.example.demojson.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public String addProduct(NewProductDto product, List<AttributeDto> attributes) {
        var newProduct = new Product();
        newProduct.setId(UUID.randomUUID().toString());
        newProduct.setAttributes(new ArrayList<>());
        newProduct.setName(product.getName());
        attributes.forEach(attr -> {
            var attribute = new Attribute();
            attribute.setAttrValue(attr.getAttrValue());
            attribute.setAttributeId(new AttributeId(newProduct, attr.getAttrName()));
            newProduct.getAttributes().add(attribute);
        });
        var saved = repository.save(newProduct);
        return saved.getId();
    }
}
