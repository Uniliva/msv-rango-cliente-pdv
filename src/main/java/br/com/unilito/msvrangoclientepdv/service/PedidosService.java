package br.com.unilito.msvrangoclientepdv.service;

import com.github.uniliva.librangobase.dto.PedidoDTO;

public interface PedidosService {
	public void salvar(PedidoDTO pedido);

	public void notificarStatusPedidos();
}
