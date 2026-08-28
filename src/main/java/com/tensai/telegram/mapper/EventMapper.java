package com.tensai.telegram.mapper;

import com.tensai.telegram.dto.event.*;
import com.tensai.telegram.dto.webhook.TelegramCallbackQuery;
import com.tensai.telegram.dto.webhook.TelegramChatMemberUpdated;
import com.tensai.telegram.dto.webhook.TelegramFile;
import com.tensai.telegram.dto.webhook.TelegramMessage;
import org.springframework.stereotype.Component;

import java.util.Comparator;

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

        if(update == null) return null;
        if (update.messageId().equals(update.messageThreadId())) return null;
        String text = update.text() != null ? update.text() :
                update.caption() != null ? update.caption() : null;

        TelegramFile file = null;
        TelegramFile temp;

        if (update.photo() != null) {
            temp = update.photo().stream()
                    .sorted(Comparator.comparing(TelegramFile::fileSize).reversed())
                    .filter(f -> f.fileSize() < 20 * 1e6)
                    .findFirst().orElse(null);
            if (temp != null) {
                file = new TelegramFile(
                        temp.fileId(),
                        temp.fileUniqueId(),
                        temp.mimeType(),
                        temp.fileSize(),
                        MediaType.IMAGE
                );
            }
        }
        if (update.video() != null) {
            temp = update.video();
            file = new TelegramFile(
                        temp.fileId(),
                        temp.fileUniqueId(),
                        temp.mimeType(),
                        temp.fileSize(),
                        MediaType.VIDEO
            );
        }
        if (update.audio() != null) {
            temp = update.audio();
            file = new TelegramFile(
                    temp.fileId(),
                    temp.fileUniqueId(),
                    temp.mimeType(),
                    temp.fileSize(),
                    MediaType.AUDIO
            );
        }
        if (update.voice() != null) {
            temp = update.voice();
            file = new TelegramFile(
                    temp.fileId(),
                    temp.fileUniqueId(),
                    temp.mimeType(),
                    temp.fileSize(),
                    MediaType.AUDIO
            );
        }
        if (update.document() != null) {
            temp = update.document();
            file = new TelegramFile(
                    temp.fileId(),
                    temp.fileUniqueId(),
                    temp.mimeType(),
                    temp.fileSize(),
                    MediaType.FILE
            );
        }
        return MessageEvent.builder()
                .chatId(update.chat().id())
                .messageThreadId(update.messageThreadId())
                .messageId(update.messageId())
                .date(update.date())
                .text(text)
                .media(file)
                .entities(update.entities())
                .replyToMessage(
                        toMessageEvent(update.replyToMessage())
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
