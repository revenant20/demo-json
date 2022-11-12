package com.example.demojson.service;

import com.example.demojson.dto.AppDto;
import com.example.demojson.entity.App;
import com.example.demojson.repository.AppRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladmihalcea.hibernate.type.json.internal.JacksonUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AppServiceImpl implements AppService {

    private final AppRepository repository;
    private final ObjectMapper mapper;

    @Override
    public AppDto getAppByReferenceId(String id) {
        App referenceById = repository.getReferenceById(id);
        AppDto appDto = new AppDto();
        appDto.setId(referenceById.getId());
        appDto.setJson(referenceById.getExternalData().toString());
        return appDto;

    }

    @Transactional
    @Override
    public AppDto getAppById(String id) {
        return repository.findById(id)
                .map(post -> {
                    AppDto appDto = new AppDto();
                    appDto.setId(post.getId());
                    appDto.setJson(post.getExternalData().toString());
                    return appDto;
                })
                .orElseThrow();
    }

    @Override
    public AppDto getAppByIdWithoutOptional(String id) {
        Optional<App> byId = repository.findById(id);
        App app = byId.orElseThrow();
        AppDto appDto = new AppDto();
        appDto.setId(app.getId());
        appDto.setJson(app.getExternalData().toString());
        return appDto;
    }

    @Override
    public AppDto getAppByCustomMethode(String id) {
        App app = repository.getById(id);
        AppDto appDto = new AppDto();
        appDto.setId(app.getId());
        appDto.setJson(app.getExternalData().toString());
        return appDto;
    }

    @Override
    @SneakyThrows
    public AppDto addApp(String id, SomeJson someJson) {
        App app = new App();
        app.setId(id);
        app.setExternalData(JacksonUtil.toJsonNode(mapper.writeValueAsString(someJson)));
        App saved = repository.save(app);
        return AppDto.builder()
                .id(saved.getId())
                .json(saved.getExternalData().toString())
                .build();
    }
}
