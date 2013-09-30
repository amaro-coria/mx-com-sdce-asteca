package mx.com.asteca.servicio;

import java.util.List;

import mx.com.asteca.comun.dto.RolesModUsuariosDTO;
import mx.com.asteca.persistencia.entidades.RolesModUsuarios;

public interface RolesModUsuariosServicio extends
		BaseService<RolesModUsuariosDTO, Integer, RolesModUsuarios> {
	RolesModUsuariosDTO buscarPorUsuario(int idUsuario);

}
