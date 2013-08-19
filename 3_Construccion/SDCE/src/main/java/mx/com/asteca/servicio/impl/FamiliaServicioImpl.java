package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.FamiliaDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.FamiliaDAO;
import mx.com.asteca.persistencia.entidades.Familia;
import mx.com.asteca.servicio.FamiliaServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FamiliaServicioImpl extends
		BaseServiceImpl<FamiliaDTO, Integer, Familia> implements
		FamiliaServicio {
	
	@Autowired
	private FamiliaDAO daoFamilia;
	
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_FAMILIA)
	private Assembler assembler;
	
	@Override
	BaseDAO getDAO() {
		return daoFamilia;
	}

	@Override
	Assembler getAssembler() {
		return assembler;
	}

}
