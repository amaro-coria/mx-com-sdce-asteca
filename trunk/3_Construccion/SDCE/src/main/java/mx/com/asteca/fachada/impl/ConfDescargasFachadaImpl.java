/**
 * 
 */
package mx.com.asteca.fachada.impl;

import mx.com.asteca.comun.dto.ConfDescargasDTO;
import mx.com.asteca.fachada.ConfDescargasFachada;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.ConfDescargasServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 *
 */
@Component
public class ConfDescargasFachadaImpl extends
		BaseFachadaImpl<ConfDescargasDTO, String> implements
		ConfDescargasFachada {

	@Autowired
	private ConfDescargasServicio servicioDescargas;
	
	@Override
	BaseService getBaseService() {
		return servicioDescargas;
	}

}
