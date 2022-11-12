package com.example.demojson.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private String id;
    private String name;
    private List<AttributeDto> attributes;
}
