package com.tensai.telegram.mapper;

import com.tensai.telegram.dto.command.*;
import com.tensai.telegram.dto.telegram_api.*;
import org.springframework.stereotype.Component;

@Component
public class CommandMapper {
    public TelegramSendMessageApiRequest toApi(SendMessageCommand command) {
        return TelegramSendMessageApiRequest.builder()
                .chatId(command.chatId())
                .text(command.text())
                .replyToMessageId(command.replyToMessageId())
                .messageThreadId(command.messageThreadId())
                .keyboard(keyboardFactory(command.keyboard())).build();
    }

    public TelegramDeleteMessageApiRequest toApi(DeleteMessageCommand command) {
        return TelegramDeleteMessageApiRequest.builder()
                .chatId(command.chatId())
                .messageId(command.messageId()).build();
    }

    public TelegramDeleteTopicApiRequest toApi(DeleteTopicCommand command) {
        return TelegramDeleteTopicApiRequest.builder()
                .chatId(command.chatId())
                .messageThreadId(command.messageThreadId()).build();
    }

    public TelegramAnswerCallbackApiRequest toApi(AnswerCallbackCommand command) {
        return TelegramAnswerCallbackApiRequest.builder()
                .callbackQueryId(command.callbackQueryId())
                .text(command.text())
                .showAlert(true).build();
    }

    public TelegramEditKeyboardApiRequest toApi(EditKeyboardCommand command) {
        return TelegramEditKeyboardApiRequest.builder()
                .chatId(command.chatId())
                .messageId(command.messageId())
                .keyboard(keyboardFactory(command.keyboard())).build();
    }

    private TelegramInlineKeyboardApi keyboardFactory(Keyboard keyboard) {
        if (keyboard == null) return null;
        return TelegramInlineKeyboardApi.builder()
                .inlineKeyboard(
                        keyboard.buttons().stream()
                        .map(inner -> inner.stream()
                                .map(this::buttonFactory).toList()
                        ).toList())
                .build();
    }

    private TelegramInlineKeyboardButtonApi buttonFactory(Button button) {
        if (button == null) return null;
        return switch (button.type()) {
            case COPY -> TelegramInlineKeyboardButtonApi.builder()
                    .text(button.text()).copyText(
                           new TelegramInlineKeyboardButtonApi.CopyText(button.value())
                    ).build();
            case URL -> TelegramInlineKeyboardButtonApi.builder()
                    .text(button.text()).url(button.value()).build();

            case CALLBACK -> TelegramInlineKeyboardButtonApi.builder()
                    .text(button.text()).callbackData(button.value()).build();
        };
    }
}
