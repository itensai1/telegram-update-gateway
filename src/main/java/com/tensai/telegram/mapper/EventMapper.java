package com.tensai.telegram.mapper;

import com.tensai.telegram.dto.event.CallbackQueryEvent;
import com.tensai.telegram.dto.event.MessageEvent;
import com.tensai.telegram.dto.event.RegisterUserEvent;
import com.tensai.telegram.dto.event.TopicEvent;
import com.tensai.telegram.dto.webhook.TelegramCallbackQuery;
import com.tensai.telegram.dto.webhook.TelegramChatMemberUpdated;
import com.tensai.telegram.dto.webhook.TelegramMessage;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public RegisterUserEvent toRegisterUserEvent(TelegramChatMemberUpdated  update) {
        return RegisterUserEvent.builder()
                .telegramUserId(update.from().id())
                .telegramGroupId(update.chat().id())
                .firstName(update.from().firstName())
                .lastName(update.from().lastName())
                .username(update.from().username())
                .build();
    }

    public TopicEvent toCreateTopicEvent(TelegramMessage  update) {
        return TopicEvent.builder()
                .chatId(update.chat().id())
                .messageThreadId(update.messageThreadId())
                .topicName(update.forumTopicCreated().name())
                .build();
    }

    public TopicEvent toUpdateTopicEvent(TelegramMessage  update) {
        return TopicEvent.builder()
                .chatId(update.chat().id())
                .messageThreadId(update.messageThreadId())
                .topicName(update.forumTopicEdited().name())
                .build();
    }
    public MessageEvent toMessageEvent(TelegramMessage  update) {
        return MessageEvent.builder()
                .chatId(update.chat().id())
                .messageThreadId(update.messageThreadId())
                .messageId(update.messageId())
                .date(update.date())
                .text(update.text())
                .caption(update.caption())
                .video(update.video())
                .audio(update.audio())
                .document(update.document())
                .photo(update.photo())
                .entities(update.entities())
                .replyToMessage(
                        update.replyToMessage()!= null ? toMessageEvent(update.replyToMessage()): null
                ).build();
    }

    public CallbackQueryEvent toCallbackQueryEvent(TelegramCallbackQuery update) {
        return CallbackQueryEvent.builder()
                .chatId(update.message().chat().id())
                .messageThreadId(update.message().messageThreadId())
                .messageId(update.message().messageId())
                .callbackQueryId(update.id())
                .data(update.data()).build();
    }
}
