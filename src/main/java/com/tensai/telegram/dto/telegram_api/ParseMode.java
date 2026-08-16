package com.tensai.telegram.dto.telegram_api;

public enum ParseMode {

    MARKDOWN("MarkdownV2"),

    HTML("HTML");

    private final String value;

    ParseMode(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}