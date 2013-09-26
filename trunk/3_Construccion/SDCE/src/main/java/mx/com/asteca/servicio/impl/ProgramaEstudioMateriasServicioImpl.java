/**
 * 
 */
package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.ProgramaEstudiosMateriasDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.ProgramaEstudioMateriaDAO;
import mx.com.asteca.persistencia.entidades.ProgramaEstMaterias;
import mx.com.asteca.servicio.ProgramaEstudiosMateriaServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author JAMARO
 *
 */
@Service
public class ProgramaEstudioMateriasServicioImpl
		extends
		BaseServiceImpl<ProgramaEstudiosMateriasDTO, Integer, ProgramaEstMaterias>
		implements ProgramaEstudiosMateriaServicio {
	
	@Autowired
	private ProgramaEstudioMateriaDAO daoPrograma;
	
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_PROGRAMA_ESTUDIOS_MATERIAS_REG)
	private Assembler assemblerPrograma;
	
	@Override
	BaseDAO getDAO() {
		return daoPrograma;
	}

	@Override
	Assembler getAssembler() {
		return assemblerPrograma;
	}

}
