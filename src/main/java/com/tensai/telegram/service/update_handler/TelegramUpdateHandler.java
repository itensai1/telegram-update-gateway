package com.tensai.telegram.service.update_handler;

import com.tensai.telegram.dto.webhook.TelegramCallbackQuery;
import com.tensai.telegram.dto.webhook.TelegramChatMemberUpdated;
import com.tensai.telegram.dto.webhook.TelegramMessage;

public interface TelegramUpdateHandler {
    void handleChatMemberUpdate(TelegramChatMemberUpdated update);
    void handleMessageUpdate(TelegramMessage update);
    void handleEditedMessageUpdate(TelegramMessage update);
    void handleCallbackQueryUpdate(TelegramCallbackQuery update);

}
