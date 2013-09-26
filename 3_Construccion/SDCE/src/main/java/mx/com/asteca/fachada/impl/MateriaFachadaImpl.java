/**
 * 
 */
package mx.com.asteca.fachada.impl;

import mx.com.asteca.comun.dto.MateriaDTO;
import mx.com.asteca.fachada.MateriaFachada;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.MateriaServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 *
 */
@Component
public class MateriaFachadaImpl extends BaseFachadaImpl<MateriaDTO, Integer>
		implements MateriaFachada {

	@Autowired
	private MateriaServicio servicioMateria;
	
	@Override
	BaseService getBaseService() {
		return servicioMateria;
	}

}
