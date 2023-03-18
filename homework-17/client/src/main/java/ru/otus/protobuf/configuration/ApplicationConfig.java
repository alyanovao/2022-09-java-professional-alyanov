package ru.otus.protobuf.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.protobuf.model.ConfigParams;

@Configuration
public class ApplicationConfig {

    @Value("${application.client.firstvalue}")
    private int clientFirstValue;

    @Value("${application.client.lastvalue}")
    private int clientLastValue;

    @Value("${application.server.firstvalue}")
    private int serverFirstValue;

    @Value("${application.server.lastvalue}")
    private int serverLastValue;

    @Bean
    public ConfigParams getConfiguration() {
        return new ConfigParams(clientFirstValue, clientLastValue, serverFirstValue, serverLastValue);
    }
}
