package com.tensai.telegram.dto.webhook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TelegramChatMember(

        String status,

        TelegramWebhookUser user,

        @JsonProperty("can_delete_messages")
        Boolean canDeleteMessages

) {}
