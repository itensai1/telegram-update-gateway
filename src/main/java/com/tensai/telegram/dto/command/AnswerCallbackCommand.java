package com.tensai.telegram.dto.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AnswerCallbackCommand(

        @NotNull(message = "required")
        @JsonProperty("callback_query_id")
        String callbackQueryId,

        String text
) {
}
