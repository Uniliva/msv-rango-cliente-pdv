package br.com.unilito.msvrangoclientepdv.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.github.uniliva.librangobase.dto.PedidoDTO;
import com.github.uniliva.librangobase.dto.ProdutoDTO;

import br.com.unilito.msvrangoclientepdv.repository.RepoCustom;

@Repository
@PropertySource("classpath:query/repo.properties")
public class RepoCustomImpl implements RepoCustom {

	@Value("${SPI.PRODUTO}")
	private String queryInsertProduto;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public void salvar(PedidoDTO pedido) {
		
		ProdutoDTO prod = pedido.getProdutos().get(0);
		
		final MapSqlParameterSource parametros = new MapSqlParameterSource();
//		parameter.addValue("codigo", prod.getCodigo());
		parametros.addValue("nome", prod.getNome());
		parametros.addValue("descricao", prod.getDescricao());
		parametros.addValue("codCategoria", prod.getCodigo());
		parametros.addValue("valor", prod.getValor());
				
		namedParameterJdbcTemplate.update(queryInsertProduto, parametros);

	}

}
