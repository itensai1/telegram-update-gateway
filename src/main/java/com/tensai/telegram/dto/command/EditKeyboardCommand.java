package com.tensai.telegram.dto.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EditKeyboardCommand(

        @NotNull(message = "required")
        @JsonProperty("chat_id")
        Long chatId,

        @NotNull(message = "required")
        @JsonProperty("message_id")
        Integer messageId,

        Keyboard keyboard
) {
}
