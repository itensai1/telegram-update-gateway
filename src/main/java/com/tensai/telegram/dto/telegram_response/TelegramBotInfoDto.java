package com.tensai.telegram.dto.telegram_response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TelegramBotInfoDto(

        @JsonProperty("id")
        Long id,

        @JsonProperty("is_bot")
        boolean bot,

        @JsonProperty("first_name")
        String firstName,

        @JsonProperty("username")
        String username

) {}