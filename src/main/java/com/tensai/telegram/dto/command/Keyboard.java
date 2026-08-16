package com.tensai.telegram.dto.command;

import java.util.List;

public record Keyboard(
        List<List<Button>> buttons
) {
}
