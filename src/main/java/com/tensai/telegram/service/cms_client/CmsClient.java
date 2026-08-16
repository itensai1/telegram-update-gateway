package com.tensai.telegram.service.cms_client;

import com.tensai.telegram.dto.event.CmsEventRequest;

public interface CmsClient {
    void forwardEventToCMS(CmsEventRequest event);
}
