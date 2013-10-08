/**
 * 
 */
package mx.com.asteca.servicio.impl;

import java.util.List;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.AdministrativoDTO;
import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.AdministrativoDAO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.entidades.Administrativos;
import mx.com.asteca.servicio.AdministrativoServicio;
import mx.com.asteca.servicio.ServicioException;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author JAMARO
 * 
 */
@Service
public class AdministrativoServicioImpl extends
		BaseServiceImpl<AdministrativoDTO, Integer, Administrativos> implements
		AdministrativoServicio {

	@Autowired
	private AdministrativoDAO daoAdmin;

	@Autowired
	@Qualifier(Constantes.ASSEMBLER_ADMINISTRATIVOS)
	private Assembler assemblerAdmin;

	@Override
	BaseDAO getDAO() {
		return daoAdmin;
	}

	@Override
	Assembler getAssembler() {
		return assemblerAdmin;
	}
	
	

	@Override
	@Transactional(readOnly = true)
	public List<AdministrativoDTO> findByNombre(String nombreCompleto)
			throws ServicioException {
		try {
			List<Administrativos> listMapping = daoAdmin
					.findByNombre(nombreCompleto);
			List<AdministrativoDTO> listDTO = assemblerAdmin
					.getDTOListTransform(listMapping);
			return listDTO;
		} catch (PersistenciaException e) {
			throw new ServicioException("Error en findByNombre:"
					+ e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<AdministrativoDTO> findByNombreClave(String nombreCompleto,
			String clave) throws ServicioException {
		try {
			List<Administrativos> listMapping = daoAdmin.findByNombreAndClave(
					nombreCompleto, clave);
			List<AdministrativoDTO> listDTO = assemblerAdmin
					.getDTOListTransform(listMapping);
			return listDTO;
		} catch (PersistenciaException e) {
			throw new ServicioException("Error en findByNombreClave:"
					+ e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<AdministrativoDTO> findByClave(String clave)
			throws ServicioException {
		try {
			List<Administrativos> listMapping = daoAdmin.findByClave(clave);
			List<AdministrativoDTO> listDTO = assemblerAdmin
					.getDTOListTransform(listMapping);
			return listDTO;
		} catch (PersistenciaException e) {
			throw new ServicioException("Error en findByClave:"
					+ e.getMessage(), e);
		}
	}

}
