package com.tensai.telegram.service.telegram_client;

import com.tensai.telegram.dto.shared.TelegramMethod;
import com.tensai.telegram.dto.telegram_api.*;
import com.tensai.telegram.dto.telegram_response.TelegramMessageApiResponse;
import com.tensai.telegram.dto.telegram_response.TelegramFileDto;
import com.tensai.telegram.dto.telegram_response.TelegramResponse;
import com.tensai.telegram.dto.telegram_response.TelegramBotInfoDto;
import com.tensai.telegram.exception.CustomException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;


@Component
public class TelegramClientImpl implements TelegramClient {

    private final RestClient telegramClient;

    public TelegramClientImpl(
            @Qualifier("telegram") RestClient restClient
    ) {
        this.telegramClient = restClient;
    }


    @Override
    public TelegramFileDto getFile(String fileId) {
        String method = "%s?file_id=%s".formatted(TelegramMethod.GET_FILE.value(), fileId);

        TelegramResponse<TelegramFileDto> response =
                get(
                        method,
                        new ParameterizedTypeReference<>() {}
                );

        return unwrap(response,TelegramMethod.GET_FILE.name());
    }

    @Override
    public Resource downloadFile(String filePath) {

        try {
            Resource resource = telegramClient.get()
                    .uri(buildFileUrl(filePath))
                    .retrieve()
                    .body(Resource.class);

            if (resource == null) {
                throw new CustomException("resource not found");
            }
            return resource;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public TelegramMessageApiResponse sendMessage(TelegramSendMessageApiRequest request) {
        TelegramResponse<TelegramMessageApiResponse> response =
                post(
                        TelegramMethod.SEND_MESSAGE.value(),
                        request,
                        new ParameterizedTypeReference<>() {}
                );

        return unwrap(response, TelegramMethod.SEND_MESSAGE.name());
    }

    @Override
    public boolean deleteTopic(TelegramDeleteTopicApiRequest request) {
        TelegramResponse<Boolean> response =
                post(
                        TelegramMethod.DELETE_FORUM_TOPIC.value(),
                        request,
                        new ParameterizedTypeReference<>() {}
                );

        return unwrap(response, TelegramMethod.DELETE_FORUM_TOPIC.name());
    }

    @Override
    public boolean deleteMessage(TelegramDeleteMessageApiRequest request) {
        TelegramResponse<Boolean> response =
                post(
                        TelegramMethod.DELETE_MESSAGE.value(),
                        request,
                        new ParameterizedTypeReference<>() {}
                );

        return unwrap(response, TelegramMethod.DELETE_MESSAGE.name());
    }

    @Override
    public TelegramMessageApiResponse editKeyboard(TelegramEditKeyboardApiRequest request) {
        TelegramResponse<TelegramMessageApiResponse> response =
                post(
                        TelegramMethod.EDIT_KEYBOARD.value(),
                        request,
                        new ParameterizedTypeReference<>() {}
                );

        return unwrap(response, TelegramMethod.EDIT_KEYBOARD.name());
    }

    @Override
    public boolean answerCallback(TelegramAnswerCallbackApiRequest request) {
        TelegramResponse<Boolean> response =
                post(
                        TelegramMethod.ANSWER_CALLBACK_QUERY.value(),
                        request,
                        new ParameterizedTypeReference<>() {}
                );

        return unwrap(response, TelegramMethod.ANSWER_CALLBACK_QUERY.name());
    }

    private <T> T get(
            String method,
            ParameterizedTypeReference<T> responseType
    ) {
        try {
            return telegramClient.get()
                    .uri(buildBotUrl(method))
                    .retrieve()
                    .body(responseType);

        } catch (Exception exception) {
            throw new CustomException(
                    "Telegram GET request failed.",
                    exception
            );
        }
    }

    private <T> T post(
            String method,
            Object request,
            ParameterizedTypeReference<T> responseType
    ) {
        try {
            if(request == null) {request = "";}
            return telegramClient.post()
                    .uri(buildBotUrl(method))
                    .body(request)
                    .retrieve()
                    .body(responseType);

        } catch (Exception exception) {
            throw new CustomException(
                    "Telegram POST request failed.",
                    exception
            );
        }
    }

    private static  <T> T unwrap(
            TelegramResponse<T> response,
            String operation
    ) {
        if (response == null) {
            throw new CustomException(
                    operation + " failed."
            );
        }

        if (!response.ok()) {
            throw new CustomException(
                    operation + ": "
                            + response.description()
            );
        }
        return response.result();
    }

    @Value("${telegram.bot-token}")
    private String BOT_TOKEN;

    private String buildBotUrl(String method) {

        return "/bot"
                + BOT_TOKEN
                + "/"
                + method;
    }

    private String buildFileUrl(String filePath) {

        return "/file/bot"
                + BOT_TOKEN
                + "/"
                + filePath;
    }

}