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
public class RetryMessageReceiver {

    private final JmsTemplate defaultJmsTemplate;

    @JmsListener(destination = "retryQueue")
    public void onMessage(Message message) throws JMSException {
        if(message instanceof TextMessage){
            TextMessage textMessage = (TextMessage) message;
            log.info("Received message {}", textMessage.getText());
            defaultJmsTemplate.convertAndSend("answerQueue", "Answer message (After retry). " + textMessage.getText());
        } else {
            log.warn("Received unknown message type {}", message.getJMSType());
        }
    }
}
