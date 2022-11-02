package com.example.demojson.controller;

import com.example.demojson.dto.AppDto;
import com.example.demojson.service.AppService;
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
public class AppController {

    private final AppService service;

    @GetMapping("/{id}/{str}")
    public ResponseEntity<?> getPost(@PathVariable String id, @PathVariable String str) {
        var postDto=  switch (str) {
            case "1" -> service.getAppByReferenceId(id);
            case "2" -> service.getAppById(id);
            case "3" -> service.getAppByIdWithoutOptional(id);
            case "4" -> service.getAppByCustomMethode(id);
            default -> throw new RuntimeException();
        };
        return ResponseEntity.ok(postDto);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody SomeJson someJson) {
        AppDto appDto = service.addApp(UUID.randomUUID().toString(), someJson);
        return ResponseEntity.ok(appDto);
    }

}
