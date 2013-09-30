package mx.com.asteca.servicio;

import java.util.List;

import mx.com.asteca.comun.dto.RolesModPermisosSobreDTO;
import mx.com.asteca.persistencia.entidades.RolesModPermisosSobre;

public interface RolesModPermisosSobreServicio extends
		BaseService<RolesModPermisosSobreDTO, Integer, RolesModPermisosSobre> {
	
	List<RolesModPermisosSobreDTO> buscarPorRolesModUsr(int idRolModUsr);

}
