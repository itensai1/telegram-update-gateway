package com.tensai.telegram.dto.shared;

public enum TelegramMethod {

    GET_FILE("getFile"),

    SEND_MESSAGE("sendMessage"),

    DELETE_MESSAGE("deleteMessage"),

    DELETE_FORUM_TOPIC("deleteForumTopic"),

    EDIT_KEYBOARD("editMessageReplyMarkup"),

    ANSWER_CALLBACK_QUERY("answerCallbackQuery");

    private final String value;

    TelegramMethod(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}