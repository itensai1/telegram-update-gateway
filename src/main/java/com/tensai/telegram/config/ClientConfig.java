package com.tensai.telegram.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientConfig {

    @Bean("telegram")
    public RestClient telegramRestClient(
            @Value("${telegram.api-url}") String telegramUrl
    ) {
        return RestClient.builder()
                .baseUrl(telegramUrl)
                .build();

    }

    @Bean("cmsApi")
    public RestClient CmsRestClient(
            @Value("${tensai-cms.api.url}")  String cmsUrl,
            @Value("${internal.api.header}") String header,
            @Value("${internal.api.secret}") String secret
    ) {
        return RestClient.builder()
                .baseUrl(cmsUrl)
                .defaultHeader(header, secret)
                .build();
    }
}
