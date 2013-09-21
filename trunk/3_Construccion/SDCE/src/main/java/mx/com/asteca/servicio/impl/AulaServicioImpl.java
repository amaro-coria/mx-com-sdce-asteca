package mx.com.asteca.servicio.impl;

import java.util.List;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.AulaDTO;
import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.AulaDAO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.entidades.Aulas;
import mx.com.asteca.servicio.AulaServicio;
import mx.com.asteca.servicio.ServicioException;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jorge Amaro Coria
 * @version 1.0
 * @since 1.0
 *
 */
@Service
public class AulaServicioImpl extends BaseServiceImpl<AulaDTO, Integer, Aulas>
		implements AulaServicio {

	@Autowired
	private AulaDAO dao;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_AULAS)
	private Assembler assembler;
	
	/* (non-Javadoc)
	 * @see mx.com.asteca.servicio.impl.BaseServiceImpl#getDAO()
	 */
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_AULAS)
	@Override
	BaseDAO getDAO() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see mx.com.asteca.servicio.impl.BaseServiceImpl#getAssembler()
	 */
	@Override
	Assembler getAssembler() {
		return assembler;
	}
	
	/* (non-Javadoc)
	 * @see mx.com.asteca.servicio.AulaServicio#findBySede(java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public List<AulaDTO> findBySede(String sede) throws ServicioException{
		try {
			List<Aulas> lisMapping = dao.findBySede(sede);
			List<AulaDTO> listDTOs = assembler.getDTOListTransform(lisMapping);
			return listDTOs;
		} catch (PersistenciaException e) {
			throw new ServicioException("Error en servicio findBySede:"+e.getMessage(), e);
		}
	}

}
