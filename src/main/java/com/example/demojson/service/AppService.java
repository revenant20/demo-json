package com.example.demojson.service;

import com.example.demojson.dto.AppDto;

public interface AppService {
    AppDto getAppByReferenceId(String id);

    AppDto getAppById(String id);

    AppDto getAppByCustomMethode(String id);

    AppDto getAppByIdWithoutOptional(String id);

    AppDto addApp(String id, SomeJson someJson);
}
