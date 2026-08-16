package com.tensai.telegram.service.webhook;

import com.tensai.telegram.dto.webhook.TelegramUpdate;
import com.tensai.telegram.exception.CustomException;
import com.tensai.telegram.service.update_handler.TelegramUpdateHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebhookServiceImpl implements WebhookService {

    private final TelegramUpdateHandler updateHandler;

    @Override
    public void dispatch(TelegramUpdate update) {

        if (update.myChatMember() != null) {

            updateHandler.handleChatMemberUpdate(update.myChatMember());
            return;
        }

        if(update.message() != null) {

            updateHandler.handleMessageUpdate(update.message());
            return;
        }
        if(update.editedMessage() != null) {

            updateHandler.handleEditedMessageUpdate(update.editedMessage());
            return;
        }

        if(update.callbackQuery() != null) {

            updateHandler.handleCallbackQueryUpdate(update.callbackQuery());
        }

    }
}
