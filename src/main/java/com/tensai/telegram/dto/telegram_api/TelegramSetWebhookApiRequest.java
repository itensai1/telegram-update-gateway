package com.tensai.telegram.dto.telegram_api;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TelegramSetWebhookApiRequest(

        String url,

        @JsonProperty("secret_token")
        String secretToken

) {
}