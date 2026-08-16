package com.tensai.telegram.service.command_handler;

import com.tensai.telegram.dto.command.*;
import com.tensai.telegram.mapper.CommandMapper;
import com.tensai.telegram.service.telegram_client.TelegramClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandHandlerImpl implements CommandHandler {
    private final TelegramClient telegramClient;
    private final CommandMapper commandMapper;

    @Override
    public void handleSendMessageCommand(SendMessageCommand command) {
        telegramClient.sendMessage(commandMapper.toApi(command));
    }

    @Override
    public void handleDeleteMessageCommand(DeleteMessageCommand command) {
        telegramClient.deleteMessage(commandMapper.toApi(command));
    }

    @Override
    public void handleDeleteTopicCommand(DeleteTopicCommand command) {
        telegramClient.deleteTopic(commandMapper.toApi(command));
    }

    @Override
    public void handleAnswerCallbackCommand(AnswerCallbackCommand command) {
        telegramClient.answerCallback(commandMapper.toApi(command));
    }

    @Override
    public void handleEditKeyboardCommand(EditKeyboardCommand command) {
        telegramClient.editKeyboard(commandMapper.toApi(command));
    }
}
