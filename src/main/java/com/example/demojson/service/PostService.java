package com.example.demojson.service;

import com.example.demojson.dto.PostDto;

public interface PostService {
    PostDto getPostByReferenceId(String id);

    PostDto getPostById(String id);

    PostDto getPostByCustomMethode(String id);

    PostDto getPostByIdWithoutOptional(String id);

    PostDto addPost(String id, SomeJson someJson);
}
