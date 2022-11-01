package com.example.demojson.service;

import com.example.demojson.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WriterPostServiceImpl implements WriterPostService {

    private final PostRepository repository;

    @Transactional
    @Override
    public void updateAuthor(String id, String newAuthor) {
        repository.findById(id)
                .ifPresent(post -> post.setAuthor(newAuthor));
    }
}