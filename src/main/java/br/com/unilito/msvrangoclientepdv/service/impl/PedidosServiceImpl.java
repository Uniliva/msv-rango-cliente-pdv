package br.com.unilito.msvrangoclientepdv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.uniliva.commonsutils.utils.JsonUtils;
import com.github.uniliva.librangobase.dto.PedidoDTO;

import br.com.unilito.msvrangoclientepdv.repository.RepoCustom;
import br.com.unilito.msvrangoclientepdv.service.PedidosService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PedidosServiceImpl implements PedidosService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	@Autowired
	private RepoCustom repo;

	@Value("${topic.kafka.fila.status}")
	private String topico;

	@Override
	@Transactional
	public void salvar(PedidoDTO pedido) {
		PedidoDTO pedidoSalvo = repo.salvar(pedido);
		repo.salvarItensPedido(pedidoSalvo);

	}

	@Override
	public void notificarStatusPedidos() {
		List<PedidoDTO> pedido = repo.recuperaPedidoANotificar();
		log.info("Postando mensagem no topico: {}", topico);
		kafkaTemplate.send(topico, JsonUtils.paraJson(pedido));
	}

}
