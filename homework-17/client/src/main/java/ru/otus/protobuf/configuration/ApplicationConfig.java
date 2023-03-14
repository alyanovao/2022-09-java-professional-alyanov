package ru.otus.protobuf.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.protobuf.model.ConfigParams;

@Configuration
public class ApplicationConfig {

    @Value("${application.client.firstvalue}")
    private Integer clientFirstValue;

    @Value("${application.client.lastvalue}")
    private Integer clientLastValue;

    @Value("${application.server.firstvalue}")
    private Integer serverFirstValue;

    @Value("${application.server.lastvalue}")
    private Integer serverLastValue;

    @Bean
    public ConfigParams getConfiguration() {
        return new ConfigParams(clientFirstValue, clientLastValue, serverFirstValue, serverLastValue);
    }
}
