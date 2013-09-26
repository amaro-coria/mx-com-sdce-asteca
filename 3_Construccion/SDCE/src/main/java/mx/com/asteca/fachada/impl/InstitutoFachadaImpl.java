/**
 * 
 */
package mx.com.asteca.fachada.impl;

import mx.com.asteca.comun.dto.InstitutoDTO;
import mx.com.asteca.fachada.InstitutoFachada;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.InstitutoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 *
 */
@Component
public class InstitutoFachadaImpl extends
		BaseFachadaImpl<InstitutoDTO, Integer> implements InstitutoFachada{

	@Autowired
	private InstitutoServicio servicioInst;
	
	@Override
	BaseService getBaseService() {
		return servicioInst;
	}

}
