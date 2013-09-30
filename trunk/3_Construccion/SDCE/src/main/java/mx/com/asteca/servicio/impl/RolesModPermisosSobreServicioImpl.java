package mx.com.asteca.servicio.impl;

import java.util.List;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.RolesModPermisosSobreDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.RolesModPermisosSobreDAO;
import mx.com.asteca.persistencia.entidades.RolesModPermisosSobre;
import mx.com.asteca.servicio.RolesModPermisosSobreServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RolesModPermisosSobreServicioImpl
		extends
		BaseServiceImpl<RolesModPermisosSobreDTO, Integer, RolesModPermisosSobre> 
		implements RolesModPermisosSobreServicio {
	
	@Autowired
	private RolesModPermisosSobreDAO rolesModPermisosSobreDAO;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_ROLES_MOD_PERMISOS_SOBRE)
	private Assembler assemblerRolesModPermisosSobre;

	@Override
	BaseDAO getDAO() {
		return rolesModPermisosSobreDAO;
	}

	@Override
	Assembler getAssembler() {
		return assemblerRolesModPermisosSobre;
	}

	@Override
	public List<RolesModPermisosSobreDTO> buscarPorRolesModUsr(int idRolModUsr) {
		return assemblerRolesModPermisosSobre.getDTOListTransform(
				rolesModPermisosSobreDAO.findByModPermisosUsr(idRolModUsr));
	}

}
