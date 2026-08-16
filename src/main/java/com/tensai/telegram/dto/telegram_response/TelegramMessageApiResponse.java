package com.tensai.telegram.dto.telegram_response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TelegramMessageApiResponse(

        @JsonProperty("message_id")
        Integer messageId

) {
}