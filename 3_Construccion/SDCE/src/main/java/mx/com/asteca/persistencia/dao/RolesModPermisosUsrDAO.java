package mx.com.asteca.persistencia.dao;

import java.util.List;

import mx.com.asteca.persistencia.entidades.RolesModPermisosUsr;

public interface RolesModPermisosUsrDAO extends
		BaseDAO<RolesModPermisosUsr, Integer> {
	List<RolesModPermisosUsr> findByRolModUsr(int idModRolUsr);
}
