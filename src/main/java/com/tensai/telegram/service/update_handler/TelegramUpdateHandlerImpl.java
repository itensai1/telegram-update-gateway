package com.tensai.telegram.service.update_handler;

import com.tensai.telegram.dto.telegram_api.TelegramSendMessageApiRequest;
import com.tensai.telegram.dto.event.CmsEventRequest;
import com.tensai.telegram.dto.event.EventType;
import com.tensai.telegram.dto.webhook.TelegramCallbackQuery;
import com.tensai.telegram.dto.webhook.TelegramChatMemberUpdated;
import com.tensai.telegram.dto.webhook.TelegramMessage;
import com.tensai.telegram.mapper.EventMapper;
import com.tensai.telegram.service.cms_client.CmsClient;
import com.tensai.telegram.service.telegram_client.TelegramClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TelegramUpdateHandlerImpl implements TelegramUpdateHandler {
    private final TelegramClient telegramClient;
    private final CmsClient cmsClient;
    private final EventMapper eventMapper;

    @Override
    public void handleChatMemberUpdate(TelegramChatMemberUpdated update) {

        if(!isAdmin(update)) {
            telegramClient.sendMessage(
                    TelegramSendMessageApiRequest.builder()
                            .chatId(update.chat().id())
                            .text("Bot needs to be an admin with the right to delete messages to manage blogs properly.")
                            .build()
            );
            return;
        }

        if (update.chat().isForum() == null || !update.chat().isForum()) {
            telegramClient.sendMessage(
                    TelegramSendMessageApiRequest.builder()
                            .chatId(update.chat().id())
                            .text("Enable Topics in the group so you can start creating blogs")
                            .build()
            );
        }

        cmsClient.forwardEventToCMS(
                CmsEventRequest.builder()
                        .eventType(EventType.REGISTER_USER)
                        .registerUser(eventMapper.toRegisterUserEvent(update))
                        .build()
        );

    }


    @Override
    public void handleMessageUpdate(TelegramMessage update) {

        // Topic created
        if(update.forumTopicCreated() != null && update.replyToMessage() == null) {

            cmsClient.forwardEventToCMS(
                    CmsEventRequest.builder()
                            .eventType(EventType.CREATE_TOPIC)
                            .createTopic(eventMapper.toCreateTopicEvent(update))
                            .build()
            );
            return;
        }

        // Topic Title Changed
        if(update.forumTopicEdited() != null) {
            // Topic emoji changed
            if(update.forumTopicEdited().name() == null)return;

            cmsClient.forwardEventToCMS(
                    CmsEventRequest.builder()
                            .eventType(EventType.UPDATE_TOPIC)
                            .updateTopic(eventMapper.toUpdateTopicEvent(update))
                            .build()
            );
            return;
        }

        // Only a kind of message left ~
        cmsClient.forwardEventToCMS(
                CmsEventRequest.builder()
                        .eventType(EventType.CREATE_MESSAGE)
                        .createMessage(eventMapper.toMessageEvent(update))
                        .build()
        );

    }

    @Override
    public void handleEditedMessageUpdate(TelegramMessage update) {
        cmsClient.forwardEventToCMS(
                CmsEventRequest.builder()
                        .eventType(EventType.UPDATE_MESSAGE)
                        .updateMessage(eventMapper.toMessageEvent(update))
                        .build()
        );
    }

    @Override
    public void handleCallbackQueryUpdate(TelegramCallbackQuery update) {
        cmsClient.forwardEventToCMS(
                CmsEventRequest.builder()
                        .eventType(EventType.CALLBACK_QUERY)
                        .callbackQuery(eventMapper.toCallbackQueryEvent(update))
                        .build()
        );
    }

    private boolean isAdmin(TelegramChatMemberUpdated update) {
        return "administrator".equals(update.newChatMember().status())
                && update.newChatMember().canDeleteMessages();
    }


}
