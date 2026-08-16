package com.tensai.telegram.dto.telegram_response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TelegramResponse<T>(

        boolean ok,

        T result,

        String description

) {
}