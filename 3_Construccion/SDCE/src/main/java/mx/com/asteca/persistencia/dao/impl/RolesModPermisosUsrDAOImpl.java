package mx.com.asteca.persistencia.dao.impl;

import org.springframework.stereotype.Repository;

import mx.com.asteca.persistencia.dao.RolesModPermisosUsrDAO;
import mx.com.asteca.persistencia.entidades.RolesModPermisosUsr;

@Repository
public class RolesModPermisosUsrDAOImpl extends
		BaseDAOImpl<RolesModPermisosUsr, Integer> implements
		RolesModPermisosUsrDAO {
}
