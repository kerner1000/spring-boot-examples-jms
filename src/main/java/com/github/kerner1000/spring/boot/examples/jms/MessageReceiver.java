package com.github.kerner1000.spring.boot.examples.jms;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@AllArgsConstructor
@Slf4j
@Component
public class MessageReceiver {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = "testQueue")
    public void onMessage(Message message) throws JMSException {
        log.info("Received message {}, sending response..", message);
        if(message instanceof TextMessage){
            TextMessage textMessage = (TextMessage) message;
            jmsTemplate.convertAndSend("answerQueue", "Message received: " + textMessage.getText());
        } else {
            log.warn("Received unknown message type {}", message.getJMSType());
        }

    }
}
