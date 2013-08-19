package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.EstatusDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.EstatusDAO;
import mx.com.asteca.persistencia.entidades.Estatus;
import mx.com.asteca.servicio.EstatusServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EstatusServicioImpl extends
		BaseServiceImpl<EstatusDTO, Short, Estatus> implements EstatusServicio {

	@Autowired
	private EstatusDAO daoEstatus;
	
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_ESTATUS)
	private Assembler assembler;
	
	@Override
	BaseDAO getDAO() {
		return daoEstatus;
	}

	@Override
	Assembler getAssembler() {
		return assembler;
	}

}
