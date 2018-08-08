package com.au.spring.boot.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.au.spring.boot.main.consumer.MessageConsumer;
import com.au.spring.boot.main.producer.MessageProducer;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
	@Autowired
	MessageProducer messageProducer;

	@Autowired
	MessageConsumer messageConsumer;

	@PostMapping("/send/message")
	public String publishMessageToTopic(@RequestBody String xmlMessage) {
		messageProducer.publishXmlMessage(xmlMessage);
		return "Published To Topic: " + xmlMessage;
	}
}
