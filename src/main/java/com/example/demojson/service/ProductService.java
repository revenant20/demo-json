package com.example.demojson.service;

import com.example.demojson.dto.AttributeDto;
import com.example.demojson.dto.NewProductDto;

import java.util.List;

public interface ProductService {

    String addProduct(NewProductDto product, List<AttributeDto> attributes);
}
