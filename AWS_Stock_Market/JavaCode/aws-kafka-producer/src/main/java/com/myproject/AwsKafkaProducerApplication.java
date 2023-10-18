package com.myproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AwsKafkaProducerApplication {

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext context =SpringApplication.run(AwsKafkaProducerApplication.class, args);

		Producer producer = context.getBean(Producer.class);

		producer.produceMessage();

		//Thread.sleep(10000);
	}

}
