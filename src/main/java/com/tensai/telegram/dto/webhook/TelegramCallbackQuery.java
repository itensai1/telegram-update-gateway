package com.tensai.telegram.dto.webhook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TelegramCallbackQuery(

        String id,

        TelegramWebhookUser from,

        String data,

        TelegramMessage message

) {}
