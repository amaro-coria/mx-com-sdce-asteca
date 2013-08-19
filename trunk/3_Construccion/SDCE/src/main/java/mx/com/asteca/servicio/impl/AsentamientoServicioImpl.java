/**
 * 
 */
package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.AsentamientoDTO;
import mx.com.asteca.persistencia.dao.AsentamientoDAO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.entidades.Asentamientos;
import mx.com.asteca.persistencia.entidades.AsentamientosId;
import mx.com.asteca.servicio.AsentamientoServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author JAMARO
 * 
 */
@Service
public class AsentamientoServicioImpl extends
		BaseServiceImpl<AsentamientoDTO, AsentamientosId, Asentamientos>
		implements AsentamientoServicio {

	@Autowired
	private AsentamientoDAO daoAsentamiento;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_ASENTAMIENTOS)
	private Assembler assembler;

	@Override
	BaseDAO getDAO() {
		return daoAsentamiento;
	}

	@Override
	Assembler getAssembler() {
		return assembler;
	}

}
