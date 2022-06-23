package com.github.kerner1000.spring.boot.examples.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Slf4j
@Component
public class MessageReceiver {

    @JmsListener(destination = "testQueue")
    public void onMessage(Message message) {
        log.info("Received message {}", message);
    }
}
