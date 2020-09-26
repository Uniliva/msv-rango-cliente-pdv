package br.com.unilito.msvrangoclientepdv.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConsumerWorker {

	@KafkaListener(topics = "${topic.kafka.pedido}", groupId = "${topic.kafka.grupo.id}")
	public void consumer(String message) {
		log.info("Consumindo: {}", message);
	}

}
