package com.example.demojson.controller;

import com.example.demojson.AbstractIntegrationTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class PostControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    @Test
    void name() {
        assertNotNull(mockMvc);
        mockMvc.perform(
                        get("/qwerty/2")
                )
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(content().json("""
                        {"id":"qwerty","json":"{\\"a\\":\\"ab\\",\\"b\\":\\"bc\\"}"}
                        """));

    }
}