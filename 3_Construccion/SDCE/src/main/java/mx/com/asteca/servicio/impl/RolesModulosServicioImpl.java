package mx.com.asteca.servicio.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.RolesModulosDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.RolesModulosDAO;
import mx.com.asteca.persistencia.entidades.RolesModulos;
import mx.com.asteca.servicio.RolesModulosServicio;
import mx.com.asteca.servicio.assembler.Assembler;

@Service
public class RolesModulosServicioImpl extends
		BaseServiceImpl<RolesModulosDTO, Integer, RolesModulos> implements
		RolesModulosServicio {

	@Autowired
	private RolesModulosDAO rolesModulosDAO;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_ROLES_MODULOS)
	private Assembler assemblerRolesModulos;
	
	@Override
	BaseDAO getDAO() {
		return rolesModulosDAO;
	}

	@Override
	Assembler getAssembler() {
		return assemblerRolesModulos;
	}

	@Override
	public List<RolesModulosDTO> buscarPorRol(int idRol) {
		
		return assemblerRolesModulos.getDTOListTransform(
				rolesModulosDAO.findByIdRol(idRol));
	}

	
}
