package br.com.unilito.msvrangoclientepdv.repository;

import java.util.List;

import com.github.uniliva.librangobase.dto.PedidoDTO;

public interface RepoCustom {

	public PedidoDTO salvar(PedidoDTO pedido);
	
	public void salvarItensPedido(PedidoDTO pedido);

	public List<PedidoDTO> recuperaPedidoANotificar();

}
