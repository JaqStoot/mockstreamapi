package com.jaqstoot.mockstreamapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MockResponse {
    private int httpStatus;
    private String payload;
    private String contentType;
}
