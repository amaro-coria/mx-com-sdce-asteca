/**
 * 
 */
package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.ProgramaEstudiosDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.ProgramaEstudiosDAO;
import mx.com.asteca.persistencia.entidades.ProgramaEstudios;
import mx.com.asteca.servicio.ProgramaEstudiosServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author JAMARO
 *
 */
@Service
public class ProgramaEstudiosServicioImpl extends
		BaseServiceImpl<ProgramaEstudiosDTO, Integer, ProgramaEstudios>
		implements ProgramaEstudiosServicio {

	@Autowired
	private ProgramaEstudiosDAO daoPrograma;
	
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_PROGRAMA_ESTUDIOS)
	private Assembler assembelrPrograma;
	
	@Override
	BaseDAO getDAO() {
		return daoPrograma;
	}

	@Override
	Assembler getAssembler() {
		return assembelrPrograma;
	}

}
