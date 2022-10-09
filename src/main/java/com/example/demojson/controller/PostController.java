package com.example.demojson.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    @GetMapping
    public ResponseEntity<?> getPost() {
        return ResponseEntity.ok().build();
    }
}
