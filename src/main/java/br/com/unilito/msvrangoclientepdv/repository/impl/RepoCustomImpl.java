package br.com.unilito.msvrangoclientepdv.repository.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.github.uniliva.librangobase.dto.PedidoDTO;
import com.github.uniliva.librangobase.dto.ProdutoDTO;
import com.github.uniliva.librangobase.entity.RegistroPedidoEntity;
import com.github.uniliva.librangobase.mapper.RegistroPedidoMapper;

import br.com.unilito.msvrangoclientepdv.repository.RepoCustom;

@Repository
@PropertySource("classpath:query/repo.properties")
public class RepoCustomImpl implements RepoCustom {

	@Value("${SPI.PEDIDO}")
	private String queryInsertPedido;

	@Value("${SPI.ITEM.PEDIDO}")
	private String queryInsertItemPedido;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private RegistroPedidoMapper mapper;

	@Override
	public PedidoDTO salvar(PedidoDTO pedido) {
		
		RegistroPedidoEntity entity = mapper.mappear(pedido);

		final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		final BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(entity);

		namedParameterJdbcTemplate.update(queryInsertPedido, parameterSource, keyHolder, new String[] { "codigo" });

		pedido.setCodigo(keyHolder.getKey().longValue());

		return pedido;
	}

	@Override
	public void salvarItensPedido(PedidoDTO pedido) {
		
		List<ProdutoDTO> produtos = pedido.getProdutos();

		jdbcTemplate.batchUpdate(queryInsertItemPedido, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1, pedido.getCodigo());
				ps.setLong(2, produtos.get(i).getCodigo());

			}

			@Override
			public int getBatchSize() {
				return produtos.size();
			}
		});

	}

}
