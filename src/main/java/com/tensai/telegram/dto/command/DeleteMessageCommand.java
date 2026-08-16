package com.tensai.telegram.dto.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DeleteMessageCommand(

        @NotNull(message = "required")
        @JsonProperty("chat_id")
        Long chatId,

        @NotNull(message = "required")
        @JsonProperty("message_id")
        Integer messageId
) {
}
