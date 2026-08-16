package com.tensai.telegram.dto.telegram_api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record TelegramSendMessageApiRequest(

        @JsonProperty("chat_id")
        Long chatId,

        String text,

        @JsonProperty("reply_to_message_id")
        Integer replyToMessageId,

        @JsonProperty("message_thread_id")
        Integer messageThreadId,

        @JsonProperty("parse_mode")
        ParseMode parseMode,

        @JsonProperty("disable_web_page_preview")
        Boolean disableWebPagePreview,

        @JsonProperty("disable_notification")
        Boolean disableNotification,

        @JsonProperty("reply_markup")
        TelegramInlineKeyboardApi keyboard

) {
}