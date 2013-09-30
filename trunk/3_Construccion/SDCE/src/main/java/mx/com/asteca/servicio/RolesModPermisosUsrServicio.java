package mx.com.asteca.servicio;

import java.util.List;

import mx.com.asteca.comun.dto.RolesModPermisosUsrDTO;
import mx.com.asteca.persistencia.entidades.RolesModPermisosUsr;

public interface RolesModPermisosUsrServicio extends
		BaseService<RolesModPermisosUsrDTO, Integer, RolesModPermisosUsr> {
	
	List<RolesModPermisosUsrDTO> buscarPorRolModUsuario(int idRolModUsuario);
}
