package com.tensai.telegram.dto.webhook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TelegramUpdate(

        @JsonProperty("update_id")
        Long updateId,

        TelegramMessage message,

        @JsonProperty("edited_message")
        TelegramMessage editedMessage,

        @JsonProperty("callback_query")
        TelegramCallbackQuery callbackQuery,

        @JsonProperty("my_chat_member")
        TelegramChatMemberUpdated myChatMember

) {}