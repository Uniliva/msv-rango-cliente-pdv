package br.com.unilito.msvrangoclientepdv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.uniliva.librangobase.dto.PedidoDTO;

import br.com.unilito.msvrangoclientepdv.repository.RepoCustom;
import br.com.unilito.msvrangoclientepdv.service.PedidosService;

@Service
public class PedidosServiceImpl implements PedidosService {

	@Autowired
	private RepoCustom repo;
	
	@Override
	@Transactional
	public void salvar(PedidoDTO pedido) {
		PedidoDTO pedidoSalvo = repo.salvar(pedido);
		repo.salvarItensPedido(pedidoSalvo);
		
	}

}
