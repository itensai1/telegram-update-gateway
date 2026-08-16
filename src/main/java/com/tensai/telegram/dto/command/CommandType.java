package com.tensai.telegram.dto.command;

public enum CommandType {
    SEND_MESSAGE,
    DELETE_MESSAGE,
    DELETE_TOPIC,
    EDIT_KEYBOARD,
    ANSWER_CALLBACK;

    public static final String SM = "SEND_MESSAGE";
    public static final String DM = "DELETE_MESSAGE";
    public static final String DT = "DELETE_TOPIC";
    public static final String EK = "EDIT_KEYBOARD";
    public static final String AC = "ANSWER_CALLBACK";

}
