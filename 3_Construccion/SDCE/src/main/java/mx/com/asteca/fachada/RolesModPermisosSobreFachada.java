package mx.com.asteca.fachada;

import java.util.List;

import mx.com.asteca.comun.dto.RolesModPermisosSobreDTO;

public interface RolesModPermisosSobreFachada extends
		BaseFachada<RolesModPermisosSobreDTO, Integer> {
	
	List<RolesModPermisosSobreDTO> buscarPorRolModPermisosUsr(int idRolModPermisosUsr);
}
