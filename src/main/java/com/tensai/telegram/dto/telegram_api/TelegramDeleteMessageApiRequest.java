package com.tensai.telegram.dto.telegram_api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record TelegramDeleteMessageApiRequest(

        @JsonProperty("chat_id")
        Long chatId,

        @JsonProperty("message_id")
        Integer messageId

) {
}