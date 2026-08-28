package com.tensai.telegram.dto.webhook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TelegramMessage(

        @JsonProperty("message_id")
        Integer messageId,

        TelegramWebhookUser from,

        TelegramChat chat,

        @JsonProperty("message_thread_id")
        Integer messageThreadId,

        @JsonProperty("forum_topic_created")
        TopicTitle forumTopicCreated,

        @JsonProperty("forum_topic_edited")
        TopicTitle forumTopicEdited,

        Integer date,

        String text,

        String caption,

        TelegramFile video,

        TelegramFile audio,

        TelegramFile voice,

        TelegramFile document,

        List<TelegramFile> photo,

        List<MessageEntity> entities,

        @JsonProperty("reply_to_message")
        TelegramMessage replyToMessage

) {
        public record TopicTitle(String name){}
}
