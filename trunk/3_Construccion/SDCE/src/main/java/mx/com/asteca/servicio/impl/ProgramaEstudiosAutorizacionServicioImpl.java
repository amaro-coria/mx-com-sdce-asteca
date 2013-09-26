/**
 * 
 */
package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.ProgramaEstudiosAutorizacionDTO;
import mx.com.asteca.persistencia.dao.AutorizacionesProgEstDAO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.entidades.AutorizacionesProgrEst;
import mx.com.asteca.servicio.ProgramaEstudioAutorizacionServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author JAMARO
 *
 */
@Service
public class ProgramaEstudiosAutorizacionServicioImpl extends
		BaseServiceImpl<ProgramaEstudiosAutorizacionDTO, Integer, AutorizacionesProgrEst> implements
		ProgramaEstudioAutorizacionServicio {

	@Autowired
	private AutorizacionesProgEstDAO dao;
	
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_AUTORIZACION_PROGR_EST)
	private Assembler assembler;
	
	@Override
	BaseDAO getDAO() {
		return dao;
	}

	@Override
	Assembler getAssembler() {
		return assembler;
	}

}
