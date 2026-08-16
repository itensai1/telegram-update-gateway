package com.tensai.telegram.dto.telegram_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record TelegramInlineKeyboardApi(

        @JsonProperty("inline_keyboard")
        List<List<TelegramInlineKeyboardButtonApi>> inlineKeyboard

) {
}