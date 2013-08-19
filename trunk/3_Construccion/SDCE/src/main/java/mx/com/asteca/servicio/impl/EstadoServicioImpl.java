/**
 * 
 */
package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.EstadoDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.EstadoDAO;
import mx.com.asteca.persistencia.entidades.Estados;
import mx.com.asteca.servicio.EstadoServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author JAMARO
 * 
 */
@Service
public class EstadoServicioImpl extends
		BaseServiceImpl<EstadoDTO, Integer, Estados> implements EstadoServicio {

	@Autowired
	private EstadoDAO daoEstado;
	
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_ESTADOS)
	private Assembler assemblerEstado;
	
	@Override
	BaseDAO getDAO() {
		return daoEstado;
	}

	@Override
	Assembler getAssembler() {
		return assemblerEstado;
	}

}
