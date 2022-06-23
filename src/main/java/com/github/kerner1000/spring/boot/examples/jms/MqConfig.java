package com.github.kerner1000.spring.boot.examples.jms;

import lombok.Data;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@Data
@EnableJms
@Configuration
@ConfigurationProperties(prefix = "messaging.in")
public class MqConfig {

    String host;
    String user;
    String password;

    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new  ActiveMQConnectionFactory();
        connectionFactory.setTrustAllPackages(true);
        connectionFactory.setBrokerURL(host);
        connectionFactory.setPassword(password);
        connectionFactory.setUserName(user);
        return connectionFactory;
    }
}
