/**
 * 
 */
package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.InstructorDTO;
import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.InstructorDAO;
import mx.com.asteca.persistencia.entidades.Instructores;
import mx.com.asteca.servicio.InstructorServicio;
import mx.com.asteca.servicio.ServicioException;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Override
	@Transactional
	public void removeDependencias(InstructorDTO dto) throws ServicioException{
		try{
			Instructores instructores = (Instructores) assemblerInstructor.getMappingTransform(dto);
			daoInstructor.removeDependencias(instructores);
		}catch(PersistenciaException e){
			throw new ServicioException("Error en  removeDependencias: "+e.getMessage(), e);
		}
	}

}
