package com.tensai.telegram.dto.command;

import jakarta.validation.constraints.NotNull;

public record Button(
        @NotNull(message = "required")
        String text,

        @NotNull(message = "required")
        ButtonType type,

        @NotNull(message = "required")
        String value
){}