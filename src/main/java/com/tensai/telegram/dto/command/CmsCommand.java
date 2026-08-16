package com.tensai.telegram.dto.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tensai.telegram.validation.NullUnless;
import com.tensai.telegram.validation.ValidateNullUnless;
import jakarta.validation.constraints.NotNull;

@ValidateNullUnless
@JsonIgnoreProperties(ignoreUnknown = true)
public record CmsCommand(

        @NotNull(message = "required")
        @JsonProperty("command_type")
        CommandType commandType,
        
        @NullUnless(field = "commandType", value = CommandType.SM)
        @JsonProperty("send_message")
        SendMessageCommand sendMessage,

        @NullUnless(field = "commandType", value = CommandType.DM)
        @JsonProperty("delete_message")
        DeleteMessageCommand deleteMessage,

        @NullUnless(field = "commandType", value = CommandType.DT)
        @JsonProperty("delete_topic")
        DeleteTopicCommand deleteTopic,

        @NullUnless(field = "commandType", value = CommandType.EK)
        @JsonProperty("edit_keyboard")
        EditKeyboardCommand  editKeyboard,

        @NullUnless(field = "commandType", value = CommandType.AC)
        @JsonProperty("answer_callback")
        AnswerCallbackCommand answerCallback
) {
}
