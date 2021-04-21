package org.dtux.agenda.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfiguration {

    @Value("${application.rest.config.timeout}")
    private long timeout;

    @Value("${application.rest.config.connectTimeout}")
    private long connectTimeout;

    @Bean
    public RestTemplate restTemplate(final RestTemplateBuilder builder) {
        final Duration readTimeout = Duration.ofMillis(timeout);
        final Duration connectTimeout = Duration.ofMillis(this.connectTimeout);
        return builder.setConnectTimeout(connectTimeout).setReadTimeout(readTimeout).build();
    }

}
