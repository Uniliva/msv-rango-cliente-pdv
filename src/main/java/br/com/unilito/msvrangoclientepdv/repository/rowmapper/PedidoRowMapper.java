package br.com.unilito.msvrangoclientepdv.repository.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.github.uniliva.librangobase.dto.PedidoDTO;
import com.github.uniliva.librangobase.dto.UsuarioDTO;
import com.github.uniliva.librangobase.enums.SexoEnum;
import com.github.uniliva.librangobase.enums.StatusPedidoEnum;

public class PedidoRowMapper implements RowMapper<PedidoDTO> {

	@Override
	public PedidoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		PedidoDTO pedido = new PedidoDTO();
		pedido.setCodigo(rs.getLong("codigo"));
		pedido.setStatus(StatusPedidoEnum.parse(rs.getInt("status_codigo")));
		
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setCodigo(rs.getLong("usuario_codigo"));
		usuario.setNome(rs.getString("usuario_nome"));
		usuario.setEmail(rs.getString("usuario_email"));
		usuario.setTelefone(rs.getString("usuario_telefone"));
		usuario.setSexo(SexoEnum.parse(rs.getInt("usuario_sexo")));
		
		pedido.setUsuario(usuario);
		
		return pedido;
	}

}
