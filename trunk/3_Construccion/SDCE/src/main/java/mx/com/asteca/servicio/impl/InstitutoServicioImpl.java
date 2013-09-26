/**
 * 
 */
package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.InstitutoDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.InstitutoDAO;
import mx.com.asteca.persistencia.entidades.Institutos;
import mx.com.asteca.servicio.InstitutoServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author JAMARO
 *
 */
@Service
public class InstitutoServicioImpl extends
		BaseServiceImpl<InstitutoDTO, Integer, Institutos> implements
		InstitutoServicio {

	@Autowired
	private InstitutoDAO daoInstituto;
	
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_INSTITUTO)
	private Assembler assemblerInstituto;
	
	@Override
	BaseDAO getDAO() {
		return daoInstituto;
	}

	@Override
	Assembler getAssembler() {
		return assemblerInstituto;
	}

}
