/**
 * 
 */
package mx.com.asteca.fachada.impl;

import java.util.List;

import mx.com.asteca.comun.dto.ReporteAulasDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.ReporteAulasFachada;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.ReporteAulaServicio;
import mx.com.asteca.servicio.ServicioException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rabelt Ibarra Godinez
 * @version 1.0
 * @since 1.0
 * 
 */
@Component
public class ReporteAulasFachadaImpl extends
		BaseFachadaImpl<ReporteAulasDTO, Integer> implements
		ReporteAulasFachada {

	@Autowired
	private ReporteAulaServicio servicio;

	@Override
	public List<ReporteAulasDTO> getAula() throws FachadaException {
		try {
			List<ReporteAulasDTO> listAulas = servicio.getAll();
			return listAulas;
		} catch (ServicioException e) {
			throw new FachadaException("Error en fachada findAula:"
					+ e.getMessage(), e);
		}
	}

	@Override
	BaseService getBaseService() {
		return servicio;
	}

}
