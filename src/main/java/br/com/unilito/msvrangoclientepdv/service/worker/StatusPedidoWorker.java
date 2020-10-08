package br.com.unilito.msvrangoclientepdv.service.worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.unilito.msvrangoclientepdv.service.PedidosService;
import lombok.extern.java.Log;

@Service
@Log
public class StatusPedidoWorker {
	
	@Autowired
	private PedidosService service;
	
	@Scheduled(cron = "${worker.status.pedido.cron}")
	public synchronized void processar() {
		log.info("Recuperando status dos pedidos");
		service.notificarStatusPedidos();		
	}

}
