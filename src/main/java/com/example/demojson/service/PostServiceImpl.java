package com.example.demojson.service;

import com.example.demojson.dto.PostDto;
import com.example.demojson.entity.Post;
import com.example.demojson.repository.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladmihalcea.hibernate.type.json.internal.JacksonUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository repository;
    private final ObjectMapper mapper;

    @Override
    public PostDto getPostByReferenceId(String id) {
        Post referenceById = repository.getReferenceById(id);
        PostDto postDto = new PostDto();
        postDto.setId(referenceById.getId());
        postDto.setJson(referenceById.getAdditionalData().toString());
        return postDto;

    }

    @Transactional
    @Override
    public PostDto getPostById(String id) {
        return repository.findById(id)
                .map(post -> {
                    PostDto postDto = new PostDto();
                    postDto.setId(post.getId());
                    postDto.setJson(post.getAdditionalData().toString());
                    return postDto;
                })
                .orElseThrow();
    }

    @Override
    public PostDto getPostByIdWithoutOptional(String id) {
        Optional<Post> byId = repository.findById(id);
        Post post = byId.orElseThrow();
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setJson(post.getAdditionalData().toString());
        return postDto;
    }

    @Override
    public PostDto getPostByCustomMethode(String id) {
        Post post = repository.getById(id);
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setJson(post.getAdditionalData().toString());
        return postDto;
    }

    @Override
    @SneakyThrows
    public PostDto addPost(String id, SomeJson someJson) {
        Post post = new Post();
        post.setId(id);
        post.setAdditionalData(JacksonUtil.toJsonNode(mapper.writeValueAsString(someJson)));
        Post saved = repository.save(post);
        return PostDto.builder()
                .id(saved.getId())
                .json(saved.getAdditionalData().toString())
                .build();
    }
}
