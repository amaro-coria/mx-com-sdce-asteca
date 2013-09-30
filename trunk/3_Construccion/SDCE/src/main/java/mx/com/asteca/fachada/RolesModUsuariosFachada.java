package mx.com.asteca.fachada;

import java.util.List;

import mx.com.asteca.comun.dto.RolesModUsuariosDTO;

public interface RolesModUsuariosFachada extends
		BaseFachada<RolesModUsuariosDTO, Integer> {

	RolesModUsuariosDTO buscarPorUsuario(int idUsuario);
}
