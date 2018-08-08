package com.au.spring.boot.main.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class MessageProducer {

	@Value("${topic.name}")
	String topicName;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void publishXmlMessage(String xmlMessage) {
		kafkaTemplate.send(topicName, xmlMessage).addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onFailure(Throwable throwable) {
				System.out.println("Error publishing " + xmlMessage);
			}

			@Override
			public void onSuccess(SendResult<String, String> stringUserSendResult) {
				System.out.println(
						"Published successfully " + xmlMessage + " to Kafka " + stringUserSendResult.toString());
			}
		});

	}
}
