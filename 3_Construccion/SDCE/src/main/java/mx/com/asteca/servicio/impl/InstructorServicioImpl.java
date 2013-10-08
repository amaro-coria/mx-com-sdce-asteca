/**
 * 
 */
package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.InstructorDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.InstructorDAO;
import mx.com.asteca.persistencia.entidades.Instructores;
import mx.com.asteca.servicio.InstructorServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author JAMARO
 *
 */
@Service
public class InstructorServicioImpl extends
		BaseServiceImpl<InstructorDTO, Integer, Instructores> implements
		InstructorServicio {

	@Autowired
	private InstructorDAO daoInstructor;
	
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_INSTRUCTORES)
	private Assembler assemblerInstructor;
	
	@Override
	BaseDAO getDAO() {
		return daoInstructor;
	}

	@Override
	Assembler getAssembler() {
		return assemblerInstructor;
	}

}
