package com.au.spring.boot.main.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

	@KafkaListener(containerFactory = "kafkaListenerContainerFactory", topics = "${topic.name}", groupId = "${consumer.group.name}")
	public void consume(String xmlMessageConsumed) {
		System.out.println("Consuming new message------" + xmlMessageConsumed);
	}

}
