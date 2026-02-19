package com.jaqstoot.mockstreamapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(MockController.class) // Focuses only on the web layer
class MockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldRegisterAndReturnMock() throws Exception {
        // 1. Arrange & Act: Configure a mock endpoint via POST
        String jsonRequest = """
                {
                  "httpStatus": 200,
                  "payload": "{\\"status\\":\\"ok\\"}",
                  "contentType": "application/json"
                }
                """;

        mockMvc.perform(post("/admin/configure")
                        .param("path", "/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk());

        // 2. Act & Assert: Call the newly mocked endpoint
        mockMvc.perform(get("/mock/test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"status\":\"ok\"}"));
    }

    @Test
    void shouldReturn404WhenMockNotFound() throws Exception {
        mockMvc.perform(get("/mock/does-not-exist"))
                .andExpect(status().isNotFound());
    }
}
