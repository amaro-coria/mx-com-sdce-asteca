/**
 * 
 */
package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.InstructorDocumentoDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.InstructorDocumentoDAO;
import mx.com.asteca.persistencia.entidades.InstructoresDocumentos;
import mx.com.asteca.servicio.InstructorDocumentoServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author JAMARO
 * 
 */
@Service
public class InstructorDocumentoServicioImpl
		extends
		BaseServiceImpl<InstructorDocumentoDTO, Integer, InstructoresDocumentos>
		implements InstructorDocumentoServicio {

	@Autowired
	private InstructorDocumentoDAO daoInstDoc;

	@Autowired
	@Qualifier(Constantes.ASSEMBLER_INSTRUCTORES_DOCUMENTOS)
	private Assembler assemblerInstDoc;

	@Override
	BaseDAO getDAO() {
		return daoInstDoc;
	}

	@Override
	Assembler getAssembler() {
		return assemblerInstDoc;
	}

}
