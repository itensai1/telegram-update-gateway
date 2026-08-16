package com.tensai.telegram.dto.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SendMessageCommand(

        @NotNull(message = "required")
        @JsonProperty("chat_id")
        Long chatId,

        @NotNull(message = "required")
        String text,

        @JsonProperty("reply_to_message_id")
        Integer replyToMessageId,

        @JsonProperty("message_thread_id")
        Integer messageThreadId,

        Keyboard keyboard
) {
}
