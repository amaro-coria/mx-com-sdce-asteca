package mx.com.asteca.servicio.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.RolesModUsuariosDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.RolesModUsuariosDAO;
import mx.com.asteca.persistencia.entidades.RolesModUsuarios;
import mx.com.asteca.servicio.RolesModUsuariosServicio;
import mx.com.asteca.servicio.assembler.Assembler;

@Service
public class RolesModUsuariosServicioImpl extends
		BaseServiceImpl<RolesModUsuariosDTO, Integer, RolesModUsuarios>
		implements RolesModUsuariosServicio {
	
	@Autowired
	private RolesModUsuariosDAO rolesModUsuariosDAO;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_ROLES_MOD_USUARIOS)
	private Assembler assemblerRolesModUsuarios;

	@Override
	BaseDAO getDAO() {
		return rolesModUsuariosDAO;
	}

	@Override
	Assembler getAssembler() {
		return assemblerRolesModUsuarios;
	}



}
