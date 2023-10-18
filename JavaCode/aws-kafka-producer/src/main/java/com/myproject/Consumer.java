package com.myproject;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
public class Consumer {

    @KafkaListener(topics = "demo_test", groupId = "consumer_group1")
    public void consume(Message message){
        System.out.println("Recieved :" + message);
    }
}
