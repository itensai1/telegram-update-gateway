package com.tensai.telegram.dto.webhook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MessageEntity(
        Integer offset,
        Integer length,
        String type
){}

