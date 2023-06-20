package com.github.kerner1000.spring.boot.examples.jms;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ScheduledMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@AllArgsConstructor
@Slf4j
@Component
public class DefaultMessageReceiver {

    private final JmsTemplate defaultJmsTemplate;

    @JmsListener(destination = "testQueue")
    public void onMessage(Message message) throws JMSException {
        if(message instanceof TextMessage){
            TextMessage textMessage = (TextMessage) message;
            log.info("Received message {}", textMessage.getText());
            if(textMessage.getText().toLowerCase().startsWith("error")){
                log.info("Sending to retry queue");
                defaultJmsTemplate.convertAndSend("retryQueue", "Send to retry: " + textMessage.getText(), new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws JMSException {
                        message.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 3000);
                        return message;
                    }
                });
            } else {
                log.info("Sending response to answer queue");
                defaultJmsTemplate.convertAndSend("answerQueue", "Answer message. " + textMessage.getText());
            }
        } else {
            log.warn("Received unknown message type {}", message.getJMSType());
        }

    }
}
