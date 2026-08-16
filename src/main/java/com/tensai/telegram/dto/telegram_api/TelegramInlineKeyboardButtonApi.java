package com.tensai.telegram.dto.telegram_api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record TelegramInlineKeyboardButtonApi(

        String text,

        @JsonProperty("callback_data")
        String callbackData,

        @JsonProperty("copy_text")
        CopyText copyText,

        String url

) {
        public record CopyText(String text) {}
}