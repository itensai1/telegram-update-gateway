package com.tensai.telegram.service.webhook;

import com.tensai.telegram.dto.webhook.TelegramUpdate;

public interface WebhookService {

    void dispatch(TelegramUpdate update) ;

}
