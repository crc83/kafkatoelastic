package org.crc83.kafkatoelastic;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class KafkatoelasticApplication {

	public static Logger logger = LoggerFactory.getLogger(KafkatoelasticApplication.class);
	private final CountDownLatch latch = new CountDownLatch(3);

	public static void main(String[] args) {
		SpringApplication.run(KafkatoelasticApplication.class, args);
	}

	@KafkaListener(topics = "dev", groupId = "default")
	public void listen(ConsumerRecord<?, ?> cr) throws Exception {
		logger.info(cr.toString());
		latch.countDown();
	}
}