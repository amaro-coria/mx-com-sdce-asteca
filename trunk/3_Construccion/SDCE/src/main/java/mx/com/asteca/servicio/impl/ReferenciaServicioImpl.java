/**
 * 
 */
package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.ReferenciaDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.ReferenciaDAO;
import mx.com.asteca.persistencia.entidades.Referencias;
import mx.com.asteca.servicio.ReferenciaServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author JAMARO
 * 
 */
@Service
public class ReferenciaServicioImpl extends
		BaseServiceImpl<ReferenciaDTO, Integer, Referencias> implements
		ReferenciaServicio {

	@Autowired
	private ReferenciaDAO daoReferencia;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_REFERENCIAS)
	private Assembler assemblerReferencia;

	@Override
	BaseDAO getDAO() {
		return daoReferencia;
	}

	@Override
	Assembler getAssembler() {
		return assemblerReferencia;
	}

}
