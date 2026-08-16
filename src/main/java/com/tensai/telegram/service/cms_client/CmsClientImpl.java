package com.tensai.telegram.service.cms_client;

import com.tensai.telegram.dto.event.CmsEventRequest;
import com.tensai.telegram.exception.CustomException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CmsClientImpl implements CmsClient {
    private final RestClient cmsClient;
    
    @Value("${tensai-cms.api.events-endpoint}")
    private String EVENTS_ENDPOINT;

    public CmsClientImpl(
            @Qualifier("cmsApi") RestClient restClient
    ) {
        this.cmsClient = restClient;
    }

    @Override
    public void forwardEventToCMS(CmsEventRequest event) {
        post(EVENTS_ENDPOINT, event);
    }

    private void post(String targetUrl, Object body) {
        try {
            cmsClient.post()
                    .uri(targetUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(body)
                    .retrieve()
                    .toBodilessEntity();

        } catch (Exception e) {
            throw new CustomException(
                    "Unexpected error during POST request to '%s': %s"
                            .formatted(targetUrl, e.getMessage()), e);
        }
    }
    
}
