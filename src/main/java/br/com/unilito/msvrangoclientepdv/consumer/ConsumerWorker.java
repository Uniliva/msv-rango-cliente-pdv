package br.com.unilito.msvrangoclientepdv.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.uniliva.commonsutils.utils.JsonUtils;
import com.github.uniliva.librangobase.dto.PedidoDTO;

import br.com.unilito.msvrangoclientepdv.service.PedidosService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConsumerWorker {
	
	@Autowired
	private PedidosService service;

	@KafkaListener(topics = "${topic.kafka.pedido}", groupId = "${topic.kafka.grupo.id}")
	public void consumer(String message) {
		log.info("Consumindo: {}", message);
		
		service.salvar(JsonUtils.paraObjViaJson(message, new TypeReference<PedidoDTO>() {
		}));
	}

}
