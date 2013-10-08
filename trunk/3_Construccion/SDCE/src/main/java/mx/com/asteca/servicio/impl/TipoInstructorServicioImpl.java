/**
 * 
 */
package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.TipoInstructorDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.TipoInstructorDAO;
import mx.com.asteca.persistencia.entidades.TiposInstructores;
import mx.com.asteca.servicio.TipoInstructorServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author JAMARO
 * 
 */
@Service
public class TipoInstructorServicioImpl extends
		BaseServiceImpl<TipoInstructorDTO, Short, TiposInstructores> implements
		TipoInstructorServicio {

	@Autowired
	private TipoInstructorDAO daoTipoInstr;

	@Autowired
	@Qualifier(Constantes.ASSEMBLER_TIPOS_INSTRUCTORES)
	private Assembler assemblerTipoInstr;

	@Override
	BaseDAO getDAO() {
		return daoTipoInstr;
	}

	@Override
	Assembler getAssembler() {
		return assemblerTipoInstr;
	}

}
