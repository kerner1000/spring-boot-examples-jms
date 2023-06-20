package com.github.kerner1000.spring.boot.examples.jms;

import lombok.Data;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

@Data
@EnableJms
@Configuration
@ConfigurationProperties(prefix = "messaging")
public class MqConfig {

    String host;
    String user;
    String password;

    @Bean
    public ConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new  ActiveMQConnectionFactory();
        connectionFactory.setTrustAllPackages(true);
        connectionFactory.setBrokerURL(host);
        connectionFactory.setPassword(password);
        connectionFactory.setUserName(user);
        return connectionFactory;
    }

    @Bean
    public JmsTemplate defaultJmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        return jmsTemplate;
    }

//    @Bean
//    public JmsTemplate delayingJmsTemplate(){
//        JmsTemplate jmsTemplate = new JmsTemplate();
//        // Throws error does not define or inherit an implementation of the resolved method 'abstract void setDeliveryDelay(long)
//        jmsTemplate.setDeliveryDelay(1000);
//        jmsTemplate.setConnectionFactory(connectionFactory());
//        return jmsTemplate;
//    }
}
