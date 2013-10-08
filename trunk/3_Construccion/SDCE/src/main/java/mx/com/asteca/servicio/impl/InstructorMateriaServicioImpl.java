/**
 * 
 */
package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.InstructorMateriaDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.InstructorMateriasDAO;
import mx.com.asteca.persistencia.entidades.InstructoresMaterias;
import mx.com.asteca.servicio.InstructorMateriaServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author JAMARO
 * 
 */
@Service
public class InstructorMateriaServicioImpl extends
		BaseServiceImpl<InstructorMateriaDTO, Integer, InstructoresMaterias>
		implements InstructorMateriaServicio {

	@Autowired
	private InstructorMateriasDAO daoInstMat;

	@Autowired
	@Qualifier(Constantes.ASSEMBLER_INSTRUCTORES_MATERIAS)
	private Assembler assemblerInstMat;

	@Override
	BaseDAO getDAO() {
		return daoInstMat;
	}

	@Override
	Assembler getAssembler() {
		return assemblerInstMat;
	}

}
