package br.com.unilito.msvrangoclientepdv.repository;

import com.github.uniliva.librangobase.dto.PedidoDTO;

public interface RepoCustom {

	public PedidoDTO salvar(PedidoDTO pedido);
	
	public void salvarItensPedido(PedidoDTO pedido);

}
