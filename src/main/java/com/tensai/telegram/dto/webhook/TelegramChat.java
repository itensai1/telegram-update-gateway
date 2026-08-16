package com.tensai.telegram.dto.webhook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TelegramChat(

        Long id,

        String title,

        String type,

        @JsonProperty("is_forum")
        Boolean isForum

) {}