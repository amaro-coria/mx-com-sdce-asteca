package mx.com.asteca.fachada;

import java.util.List;

import mx.com.asteca.comun.dto.RolesModPermisosUsrDTO;

public interface RolesModPermisosUsrFachada extends
		BaseFachada<RolesModPermisosUsrDTO, Integer> {
	
	List<RolesModPermisosUsrDTO> buscarPorRolModUsuario(int idRolModUsuarios);

}
