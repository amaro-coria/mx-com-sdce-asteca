package mx.com.asteca.servicio.impl;

import java.util.List;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.EquipoDTO;
import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.EquipoDAO;
import mx.com.asteca.persistencia.entidades.Equipos;
import mx.com.asteca.servicio.EquipoServicio;
import mx.com.asteca.servicio.ServicioException;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de equipos
 * @author Jorge Amaro Coria
 * @since 1.0
 * @version 1.0
 */
@Service
public class EquipoServicioImpl extends
		BaseServiceImpl<EquipoDTO, Integer, Equipos> implements EquipoServicio {

	@Autowired
	private EquipoDAO equipoDAO;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_EQUIPO)
	private Assembler assembler;

	@Override
	BaseDAO getDAO() {
		return equipoDAO;
	}

	@Override
	Assembler getAssembler() {
		return assembler;
	}

	/* (non-Javadoc)
	 * @see mx.com.asteca.servicio.EquipoServicio#findByCve(java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public List<EquipoDTO> findByCve(String cve) throws ServicioException {
		try {
			List<Equipos> listaMapping = equipoDAO.findByClave(cve);
			List<EquipoDTO> listaDTOs = assembler
					.getDTOListTransform(listaMapping);
			return listaDTOs;
		} catch (PersistenciaException ex) {
			throw new ServicioException("Error obteniendo la lista del dao:"
					+ ex.getMessage(), ex);
		}
	}
	
	/* (non-Javadoc)
	 * @see mx.com.asteca.servicio.EquipoServicio#findByCveAndDsc(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public List<EquipoDTO> findByCveAndDsc(String cve, String dsc) throws ServicioException {
		try {
			List<Equipos> listaMapping = equipoDAO.findByClaveAndDsc(cve, dsc);
			List<EquipoDTO> listaDTOs = assembler
					.getDTOListTransform(listaMapping);
			return listaDTOs;
		} catch (PersistenciaException ex) {
			throw new ServicioException("Error obteniendo la lista del dao:"
					+ ex.getMessage(), ex);
		}
	}
	
	/* (non-Javadoc)
	 * @see mx.com.asteca.servicio.EquipoServicio#findByCveDscAndTipo(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public List<EquipoDTO> findByCveDscAndTipo(String cve, String dsc, String tipo) throws ServicioException {
		try {
			List<Equipos> listaMapping = equipoDAO.findByClaveDscAndTipo(cve, dsc, tipo);
			List<EquipoDTO> listaDTOs = assembler
					.getDTOListTransform(listaMapping);
			return listaDTOs;
		} catch (PersistenciaException ex) {
			throw new ServicioException("Error obteniendo la lista del dao:"
					+ ex.getMessage(), ex);
		}
	}

}
