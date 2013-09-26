/**
 * 
 */
package mx.com.asteca.fachada.impl;

import mx.com.asteca.comun.dto.ModuloDTO;
import mx.com.asteca.fachada.ModuloFachada;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.ModuloServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 *
 */
@Component
public class ModuloFachadaImpl extends BaseFachadaImpl<ModuloDTO, Integer>
		implements ModuloFachada {

	@Autowired
	private ModuloServicio servicioModulo;
	
	@Override
	BaseService getBaseService() {
		return servicioModulo;
	}

}
