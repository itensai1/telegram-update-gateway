package com.tensai.telegram.service.command_handler;

import com.tensai.telegram.dto.command.*;

public interface CommandHandler {
    void handleSendMessageCommand (SendMessageCommand command);
    void handleDeleteMessageCommand (DeleteMessageCommand command);
    void handleDeleteTopicCommand(DeleteTopicCommand command);
    void handleAnswerCallbackCommand(AnswerCallbackCommand command);
    void handleEditKeyboardCommand(EditKeyboardCommand command);
}
