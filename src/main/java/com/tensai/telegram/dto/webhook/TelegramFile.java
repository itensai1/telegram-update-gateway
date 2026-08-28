package com.tensai.telegram.dto.webhook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tensai.telegram.dto.event.MediaType;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TelegramFile(

        @JsonProperty("file_id")
        String fileId,

        @JsonProperty("file_unique_id")
        String fileUniqueId,

        @JsonProperty("mime_type")
        String mimeType,

        @JsonProperty("file_size")
        Long fileSize,

        MediaType type

) {}