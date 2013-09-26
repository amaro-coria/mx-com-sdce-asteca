/**
 * 
 */
package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.ConfDescargasDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.ConfDescargasDAO;
import mx.com.asteca.persistencia.entidades.ConfDescargas;
import mx.com.asteca.servicio.ConfDescargasServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author JAMARO
 *
 */
@Service
public class ConfDescargasServicioImpl extends
		BaseServiceImpl<ConfDescargasDTO, String, ConfDescargas> implements
		ConfDescargasServicio {

	@Autowired
	private ConfDescargasDAO daoDescargas;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_CONF_DESCARGAS)
	private Assembler assemblerDescargas;
	
	@Override
	BaseDAO getDAO() {
		return daoDescargas;
	}

	@Override
	Assembler getAssembler() {
		return assemblerDescargas;
	}

}
