/**
 * 
 */
package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.DomicilioDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.DomicilioDAO;
import mx.com.asteca.persistencia.entidades.Domicilios;
import mx.com.asteca.servicio.DomicilioServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author JAMARO
 * 
 */
@Service
public class DomicilioServicioImpl extends
		BaseServiceImpl<DomicilioDTO, Integer, Domicilios> implements
		DomicilioServicio {

	@Autowired
	private DomicilioDAO daoDomicilio;

	@Autowired
	@Qualifier(Constantes.ASSEMBLER_DOMICILIO)
	private Assembler assembler;

	@Override
	BaseDAO getDAO() {
		return daoDomicilio;
	}

	@Override
	Assembler getAssembler() {
		return assembler;
	}

}
