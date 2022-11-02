package com.example.demojson.service;

import com.example.demojson.repository.AppRepository;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateAppServiceImpl implements UpdateAppService {

    private final AppRepository repository;

    @Transactional
    @Override
    public void updateAuthor(String id, String newAuthor) {
        repository.findById(id)
                .ifPresent(post -> {
                    post.setNumber(newAuthor);
                    ((ObjectNode)post.getAdditionalData()).put("str", "zxc");
                });
    }
}
