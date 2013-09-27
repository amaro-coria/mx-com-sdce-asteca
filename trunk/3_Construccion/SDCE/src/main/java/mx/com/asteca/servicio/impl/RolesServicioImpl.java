package mx.com.asteca.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.RolesDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.RolesDAO;
import mx.com.asteca.persistencia.entidades.Roles;
import mx.com.asteca.servicio.RolesServicio;
import mx.com.asteca.servicio.ServicioException;
import mx.com.asteca.servicio.assembler.Assembler;

@Service
public class RolesServicioImpl extends
		BaseServiceImpl<RolesDTO, Integer, Roles> implements RolesServicio {

	@Autowired
	private RolesDAO rolesDAO;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_ROLES)
	private Assembler assemblerRoles;
	
	@Override
	BaseDAO getDAO() {
		return rolesDAO;
	}

	@Override
	Assembler getAssembler() {
		return assemblerRoles;
	}

}
