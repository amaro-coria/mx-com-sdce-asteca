package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.EquipoDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.EquipoDAO;
import mx.com.asteca.persistencia.entidades.Equipos;
import mx.com.asteca.servicio.EquipoServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EquipoServicioImpl extends
		BaseServiceImpl<EquipoDTO, Integer, Equipos> implements 
		EquipoServicio{

	@Autowired
	private EquipoDAO equipoDAO;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_EQUIPOS)
	private Assembler assemblerEquipo;

	@Override
	BaseDAO getDAO() {
		return equipoDAO;
	}

	@Override
	Assembler getAssembler() {
		return assemblerEquipo;
	}

}
