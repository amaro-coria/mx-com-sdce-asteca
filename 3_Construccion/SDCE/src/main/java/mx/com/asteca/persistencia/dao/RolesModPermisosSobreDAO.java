package mx.com.asteca.persistencia.dao;

import java.util.List;

import mx.com.asteca.persistencia.entidades.RolesModPermisosSobre;

public interface RolesModPermisosSobreDAO extends
		BaseDAO<RolesModPermisosSobre, Integer> {
	
	List<RolesModPermisosSobre> findByModPermisosUsr(int idRolModPermisosUsr);
}
