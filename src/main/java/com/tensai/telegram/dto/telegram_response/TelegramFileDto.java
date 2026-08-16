package com.tensai.telegram.dto.telegram_response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TelegramFileDto(

        @JsonProperty("file_id")
        String fileId,

        @JsonProperty("file_unique_id")
        String fileUniqueId,

        @JsonProperty("file_size")
        Long fileSize,

        @JsonProperty("file_path")
        String filePath

) {
}
