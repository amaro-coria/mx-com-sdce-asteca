/**
 * 
 */
package mx.com.asteca.fachada.impl;

import mx.com.asteca.comun.dto.MateriaRegistroDTO;
import mx.com.asteca.fachada.MateriaRegistroFachada;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.MateriaRegistroServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 *
 */
@Component
public class MateriaRegistroFachadaImpl extends
		BaseFachadaImpl<MateriaRegistroDTO, Integer> implements
		MateriaRegistroFachada {

	@Autowired
	private MateriaRegistroServicio servicioMateriaReg;
	
	@Override
	BaseService getBaseService() {
		return servicioMateriaReg;
	}

}
