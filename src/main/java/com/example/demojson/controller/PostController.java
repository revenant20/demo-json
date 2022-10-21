package com.example.demojson.controller;

import com.example.demojson.dto.PostDto;
import com.example.demojson.service.PostService;
import com.example.demojson.service.SomeJson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService service;

    @GetMapping("/{id}/{str}")
    public ResponseEntity<?> getPost(@PathVariable String id, @PathVariable String str) {
        var postDto=  switch (str) {
            case "1" -> service.getPostByReferenceId(id);
            case "2" -> service.getPostById(id);
            case "3" -> service.getPostByIdWithoutOptional(id);
            case "4" -> service.getPostByCustomMethode(id);
            default -> throw new RuntimeException();
        };
        return ResponseEntity.ok(postDto);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody SomeJson someJson) {
        PostDto postDto = service.addPost(UUID.randomUUID().toString(), someJson);
        return ResponseEntity.ok(postDto);
    }

}
