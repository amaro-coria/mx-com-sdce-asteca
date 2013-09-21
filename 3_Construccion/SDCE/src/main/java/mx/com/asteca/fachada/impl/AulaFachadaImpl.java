/**
 * 
 */
package mx.com.asteca.fachada.impl;

import java.util.List;

import mx.com.asteca.comun.dto.AulaDTO;
import mx.com.asteca.comun.dto.CatGralDTO;
import mx.com.asteca.fachada.AulaFachada;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.servicio.AulaServicio;
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
public class AulaFachadaImpl extends BaseFachadaImpl<AulaDTO, Integer> implements AulaFachada{

	@Autowired
	private AulaServicio servicio;
	
	@Autowired
	private CatGralServicio servicioCatGral;
	
	/* (non-Javadoc)
	 * @see mx.com.asteca.fachada.impl.BaseFachadaImpl#getBaseService()
	 */
	@Override
	BaseService getBaseService() {
		return servicio;
	}
	
	/* (non-Javadoc)
	 * @see mx.com.asteca.fachada.AulaFachada#getSedes()
	 */
	@Override
	public List<CatGralDTO> getSedes() throws FachadaException{
		try {
			List<CatGralDTO> listaSedes = servicioCatGral.findTiposSede();
			return listaSedes;
		} catch (ServicioException e) {
			throw new FachadaException("Error obteniendo la lista de sedes en servicio:"+e.getMessage(), e);
		}
	}
	
	/* (non-Javadoc)
	 * @see mx.com.asteca.fachada.AulaFachada#findBySede(java.lang.String)
	 */
	@Override
	public List<AulaDTO> findBySede(String sede) throws FachadaException{
		try {
			List<AulaDTO> listAulas = servicio.findBySede(sede);
			return listAulas;
		} catch (ServicioException e) {
			throw new FachadaException("Error en fachada findBySede:"+e.getMessage(), e);
		}
	}

}
