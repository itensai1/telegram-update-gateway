package com.tensai.telegram.dto.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tensai.telegram.dto.webhook.MessageEntity;
import com.tensai.telegram.dto.webhook.TelegramFile;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record MessageEvent(

        @JsonProperty("chat_id")
        Long chatId,

        @JsonProperty("message_thread_id")
        Integer messageThreadId,

        @JsonProperty("message_id")
        Integer messageId,

        Integer date,

        String text,

        String caption,

        TelegramFile video,

        TelegramFile audio,

        TelegramFile document,

        List<TelegramFile> photo,

        List<MessageEntity> entities,

        @JsonProperty("reply_to_message")
        MessageEvent replyToMessage


) {
}
