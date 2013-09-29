package mx.com.asteca.fachada.impl;

import java.util.List;

import mx.com.asteca.comun.dto.CatGralDTO;
import mx.com.asteca.fachada.CatGralFachada;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.CatGralServicio;
import mx.com.asteca.servicio.ServicioException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jorge Amaro Coria
 * @version 1.0
 * @since 1.0
 *
 */
@Component
public class CatGralFachadaImpl extends BaseFachadaImpl<CatGralDTO, Integer> implements CatGralFachada{

	@Autowired
	private CatGralServicio servicio;
	
	@Override
	BaseService getBaseService() {
		return servicio;
	}
	
	
	/* (non-Javadoc)
	 * @see mx.com.asteca.fachada.CatGralFachada#findByCve(java.lang.String)
	 */
	@Override
	public List<CatGralDTO> findByCve(String clave) throws FachadaException{
		try {
			List<CatGralDTO> listaDTO = servicio.findByCve(clave);
			return listaDTO;
		} catch (ServicioException e) {
			throw new FachadaException("Error del servicio:"+e.getMessage(), e);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see mx.com.asteca.fachada.CatGralFachada#findByCveAndDsc(java.lang.String, java.lang.String)
	 */
	@Override
	public List<CatGralDTO> findByCveAndDsc(String cve, String dsc) throws FachadaException{
		try {
			List<CatGralDTO> listaDTOs = servicio.findByCveAndDsc(cve, dsc);
			return listaDTOs;
		} catch (ServicioException e) {
			throw new FachadaException("Error del servicio:"+e.getMessage(), e);
		}
	}
	
	/* (non-Javadoc)
	 * @see mx.com.asteca.fachada.CatGralFachada#findByCveDscAndEstatus(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<CatGralDTO> findByCveDscAndEstatus(String cve, String dsc, String estatus) throws FachadaException{
		try {
			List<CatGralDTO> listaDTOs = servicio.findByCveDscAndEstatus(cve, dsc, estatus);
			return listaDTOs;
		} catch (ServicioException e) {
			throw new FachadaException("Error del servicio:"+e.getMessage(), e);
		}
	}


	@Override
	public List<CatGralDTO> findByTiposArea() throws FachadaException {
		try {
			List<CatGralDTO> listaDTOs = servicio.findTiposArea();
			return listaDTOs;
		} catch (ServicioException e) {
			throw new FachadaException("Error del servicio:"+ e.getMessage(), e);
		}
	}


	@Override
	public List<CatGralDTO> findByTiposSede() throws FachadaException {
		try {
			List<CatGralDTO> listaDTOs = servicio.findTiposSede();
			return listaDTOs;
		} catch (ServicioException e) {
			throw new FachadaException("Error del servicio:"+ e.getMessage(), e);
		}
	}
	

	
}
