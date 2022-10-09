package com.example.demojson.service;

import com.example.demojson.dto.PostDto;
import com.example.demojson.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository repository;

    @Override
    public PostDto getPost(String id) {
        return repository.findById(id)
                .map(post -> {
                    PostDto postDto = new PostDto();
                    postDto.setId(post.getId());
                    postDto.setJson(post.getSomeData());
                    return postDto;
                })
                .orElseThrow();

    }
}
