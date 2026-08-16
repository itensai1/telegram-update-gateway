package com.tensai.telegram.controller;

import com.tensai.telegram.dto.webhook.TelegramUpdate;
import com.tensai.telegram.service.webhook.WebhookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/telegram")
@RequiredArgsConstructor
public class WebhookController {

    private final WebhookService webhookService;

    @PostMapping("/webhook")
    public ResponseEntity<Void> receiveUpdate(
            @RequestBody TelegramUpdate update
    ) {
        webhookService.dispatch(update);
        return ResponseEntity.ok().build();
    }

}