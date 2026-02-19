package com.jaqstoot.mockstreamapi.controller;

import com.jaqstoot.mockstreamapi.model.MockResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@Slf4j
public class MockController {

    // in-memory registry to hold mock configurations
    private final Map<String, MockResponse> registry = new ConcurrentHashMap<>();

    // endpoint to register new mock behavior
    @PostMapping("/admin/configure")
    public ResponseEntity<String> configure(@RequestParam String path, @RequestBody MockResponse response) {
        log.info("Configuring mock for path: {}", path);
        registry.put(path, response);
        return ResponseEntity.ok("Mock configured for " + path);
    }

    @RequestMapping("/mock/**")
    public ResponseEntity<String> handleMock(HttpServletRequest request) {
        // extract path after "/mock"
        String fullPath = request.getRequestURI();
        String mockPath = fullPath.replaceFirst("/mock", "");

        log.info("Intercepted mock request for: {}", mockPath);

        MockResponse mock = registry.get(mockPath);

        if (mock == null) {
            return ResponseEntity.status(404).body("No mock configured for " + mockPath);
        }

        return ResponseEntity
                .status(mock.getHttpStatus())
                .header("Content-Type", mock.getContentType())
                .body(mock.getPayload());
    }
}
