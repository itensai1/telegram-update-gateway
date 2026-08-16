package com.tensai.telegram.service.internal;

import com.tensai.telegram.dto.command.CmsCommand;
import com.tensai.telegram.dto.telegram_response.TelegramFileDto;
import com.tensai.telegram.service.command_handler.CommandHandler;
import com.tensai.telegram.service.telegram_client.TelegramClient;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InternalServiceImpl implements InternalService {
    private final TelegramClient telegramClient;
    private final CommandHandler commandHandler;

    @Override
    public void dispatch(CmsCommand command) {

        switch (command.commandType()){
            case SEND_MESSAGE -> commandHandler.handleSendMessageCommand(command.sendMessage());
            case DELETE_MESSAGE -> commandHandler.handleDeleteMessageCommand(command.deleteMessage());
            case DELETE_TOPIC ->  commandHandler.handleDeleteTopicCommand(command.deleteTopic());
            case EDIT_KEYBOARD -> commandHandler.handleEditKeyboardCommand(command.editKeyboard());
            case ANSWER_CALLBACK -> commandHandler.handleAnswerCallbackCommand(command.answerCallback());
        }
    }

    @Override
    public Resource getFileResource(String fileId) {

        TelegramFileDto file = telegramClient.getFile(fileId);

        return telegramClient.downloadFile(file.filePath());
    }
}
