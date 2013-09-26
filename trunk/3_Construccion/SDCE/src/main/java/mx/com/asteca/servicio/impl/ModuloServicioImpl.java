/**
 * 
 */
package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.ModuloDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.ModuloDAO;
import mx.com.asteca.persistencia.entidades.Modulos;
import mx.com.asteca.servicio.ModuloServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author JAMARO
 *
 */
@Service
public class ModuloServicioImpl extends
		BaseServiceImpl<ModuloDTO, Integer, Modulos> implements ModuloServicio {

	@Autowired
	private ModuloDAO daoModulo;
	
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_MODULO)
	private Assembler asseblerModulo;
	
	@Override
	BaseDAO getDAO() {
		return daoModulo;
	}

	@Override
	Assembler getAssembler() {
		return asseblerModulo;
	}

}
