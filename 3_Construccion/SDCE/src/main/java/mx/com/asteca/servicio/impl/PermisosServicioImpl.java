package mx.com.asteca.servicio.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.PermisosDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.PermisosDAO;
import mx.com.asteca.persistencia.entidades.Permisos;
import mx.com.asteca.servicio.PermisosServicio;
import mx.com.asteca.servicio.assembler.Assembler;

@Service
public class PermisosServicioImpl extends
		BaseServiceImpl<PermisosDTO, Integer, Permisos> implements
		PermisosServicio {
	@Autowired
	private PermisosDAO permisosDAO;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_PERMISOS)
	private Assembler assemblerPermisos;

	@Override
	BaseDAO getDAO() {
		return permisosDAO;
	}

	@Override
	Assembler getAssembler() {
		return assemblerPermisos;
	}

}
