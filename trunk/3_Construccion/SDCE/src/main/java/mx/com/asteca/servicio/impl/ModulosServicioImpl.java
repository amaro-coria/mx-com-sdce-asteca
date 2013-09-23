package mx.com.asteca.servicio.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.ModulosDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.ModulosDAO;
import mx.com.asteca.persistencia.entidades.Modulos;
import mx.com.asteca.servicio.ModulosServicio;
import mx.com.asteca.servicio.assembler.Assembler;

@Service
public class ModulosServicioImpl extends
		BaseServiceImpl<ModulosDTO, Integer, Modulos> implements
		ModulosServicio {
	
	@Autowired
	private ModulosDAO modulosDAO;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_MODULOS)
	private Assembler assemblerModulos;

	@Override
	BaseDAO getDAO() {
		return modulosDAO;
	}

	@Override
	Assembler getAssembler() {
		return assemblerModulos;
	}

}
