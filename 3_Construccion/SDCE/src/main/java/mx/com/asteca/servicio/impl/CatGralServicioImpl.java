/**
 * 
 */
package mx.com.asteca.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.CatGralDTO;
import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.CatGralDAO;
import mx.com.asteca.persistencia.entidades.CatGral;
import mx.com.asteca.servicio.CatGralServicio;
import mx.com.asteca.servicio.ServicioException;
import mx.com.asteca.servicio.assembler.Assembler;

/**
 * @author Jorge Amaro Coria
 * @version 1.0
 * @since 1.0
 *
 */
@Service
public class CatGralServicioImpl extends
		BaseServiceImpl<CatGralDTO, Integer, CatGral> implements
		CatGralServicio {
	

	@Autowired
	private CatGralDAO catGralDAO;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_CATGRAL)
	private Assembler assemblerCatGral;
	
	@Override
	BaseDAO getDAO() {
		return catGralDAO;
	}

	@Override
	Assembler getAssembler() {
		return assemblerCatGral;
	}
	
	/* (non-Javadoc)
	 * @see mx.com.asteca.servicio.CatGralServicio#findByCve(java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public List<CatGralDTO> findByCve(String cve) throws ServicioException{
		try {
			List<CatGral> listaMapping = catGralDAO.findByCve(cve);
			List<CatGralDTO> listaDTO = assemblerCatGral.getDTOListTransform(listaMapping);
			return listaDTO;
		} catch (PersistenciaException e) {
			throw new ServicioException("Error obteniendo la lista del dao"+e.getMessage(), e);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see mx.com.asteca.servicio.CatGralServicio#findByCveAndDsc(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public List<CatGralDTO> findByCveAndDsc(String cve, String dsc) throws ServicioException{
		try {
			List<CatGral> listaMapping = catGralDAO.findByCveAndDsc(cve, dsc);
			List<CatGralDTO> listaDTOs = assemblerCatGral.getDTOListTransform(listaMapping);
			return listaDTOs;
		} catch (PersistenciaException e) {
			throw new ServicioException("Error obteniendo la lista del dao"+e.getMessage(), e);
		}
	}
	
	/* (non-Javadoc)
	 * @see mx.com.asteca.servicio.CatGralServicio#findByCveDscAndEstatus(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public List<CatGralDTO> findByCveDscAndEstatus(String cve, String dsc, String estatus) throws ServicioException{
		try {
			List<CatGral> listaMapping = catGralDAO.findByCveDscAndEstatus(cve, dsc, estatus);
			List<CatGralDTO> listaDTOs = assemblerCatGral.getDTOListTransform(listaMapping);
			return listaDTOs;
		} catch (PersistenciaException e) {
			throw new ServicioException("Error obteniendo la lista del dao"+e.getMessage(), e);
		}
	}

}
