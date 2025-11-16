package jmpolak.task_notification.infrastructure.brevo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import brevo.ApiClient;
import brevo.Configuration;
import brevo.auth.ApiKeyAuth;
import brevoApi.TransactionalEmailsApi;

@org.springframework.context.annotation.Configuration
public class BrevoConfig {

    @Value("${brevo.api-key}")
    String apiKey;

    @Bean
    TransactionalEmailsApi transactionalEmailsApi() {
        ApiClient client = Configuration.getDefaultApiClient();
        ApiKeyAuth apiKeyAuth = (ApiKeyAuth) client.getAuthentication("api-key");
        apiKeyAuth
                .setApiKey(this.apiKey);
        return new TransactionalEmailsApi(client);
    }

}
