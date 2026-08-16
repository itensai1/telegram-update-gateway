package com.tensai.telegram.dto.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record CmsEventRequest(

        @JsonProperty("event_type")
        EventType eventType,

        @JsonProperty("register_user")
        RegisterUserEvent registerUser,

        @JsonProperty("create_topic")
        TopicEvent createTopic,

        @JsonProperty("update_topic")
        TopicEvent updateTopic,

        @JsonProperty("create_message")
        MessageEvent createMessage,

        @JsonProperty("update_message")
        MessageEvent updateMessage,

        @JsonProperty("callback_query")
        CallbackQueryEvent callbackQuery
) {
}
