package mx.com.asteca.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.RolesModPermisosUsrDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.RolesModPermisosUsrDAO;
import mx.com.asteca.persistencia.entidades.RolesModPermisosUsr;
import mx.com.asteca.servicio.RolesModPermisosUsrServicio;
import mx.com.asteca.servicio.assembler.Assembler;


@Service
public class RolesModPermisosUsrServicioImpl extends
		BaseServiceImpl<RolesModPermisosUsrDTO, Integer, RolesModPermisosUsr> 
		implements RolesModPermisosUsrServicio{

	@Autowired
	private RolesModPermisosUsrDAO rolesModPermisosUsrDAO;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_ROLES_MOD_PERMISOS_USR)
	private Assembler assemblerRolesModPermisosUsr;
	
	@Override
	BaseDAO getDAO() {
		return rolesModPermisosUsrDAO;
	}

	@Override
	Assembler getAssembler() {
		return assemblerRolesModPermisosUsr;
	}

	@Override
	public List<RolesModPermisosUsrDTO> buscarPorRolModUsuario(
			int idRolModUsuario) {
		return assemblerRolesModPermisosUsr.getDTOListTransform(
				rolesModPermisosUsrDAO.findByRolModUsr(idRolModUsuario));
	}

}
