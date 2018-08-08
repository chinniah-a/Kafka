package com.au.spring.boot.main.consumer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

	@KafkaListener(containerFactory = "kafkaListenerContainerFactory", topics = "${topic.name}", groupId = "${consumer.group.name}")
	public void consume(String xmlMessageConsumed) {
		System.out.println("Consuming new message------" + xmlMessageConsumed);
		invokeSoapService(xmlMessageConsumed);
	}
	private String invokeSoapService(String soapEnvelop) {
		SOAPMessage response = null;
		try {
			SOAPConnectionFactory sfc = SOAPConnectionFactory.newInstance();
		    SOAPConnection connection = sfc.createConnection();
		    InputStream is = new ByteArrayInputStream(soapEnvelop.getBytes());
		    SOAPMessage request = MessageFactory.newInstance().createMessage(null, is);
			System.out.println("\n Soap Request:\n");
		    request.writeTo(System.out);
			System.out.println();
			URL endpoint = new URL("http://10.163.177.100:9081/FSUWebSrv/CLIService");
			response = connection.call(request, endpoint);
			System.out.println("\n Soap Response:\n");
			response.writeTo(System.out);
            System.out.println();
			return response.getSOAPBody().toString();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
