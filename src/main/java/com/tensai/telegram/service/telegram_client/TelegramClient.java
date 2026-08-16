package com.tensai.telegram.service.telegram_client;

import com.tensai.telegram.dto.telegram_api.*;
import com.tensai.telegram.dto.telegram_response.TelegramMessageApiResponse;
import com.tensai.telegram.dto.telegram_response.TelegramFileDto;
import com.tensai.telegram.dto.telegram_response.TelegramBotInfoDto;
import org.springframework.core.io.Resource;

public interface TelegramClient {

    TelegramFileDto getFile(String fileId);

    Resource downloadFile(String filePath);

    TelegramMessageApiResponse sendMessage(TelegramSendMessageApiRequest request);

    boolean deleteTopic(TelegramDeleteTopicApiRequest request);

    boolean deleteMessage(TelegramDeleteMessageApiRequest request);

    TelegramMessageApiResponse editKeyboard(TelegramEditKeyboardApiRequest request);

    boolean answerCallback(TelegramAnswerCallbackApiRequest request);

}