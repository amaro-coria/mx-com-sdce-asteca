/**
 * 
 */
package mx.com.asteca.fachada.impl;

import java.util.List;

import mx.com.asteca.comun.dto.MateriaRegistroDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.MateriaRegistroFachada;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.MateriaRegistroServicio;
import mx.com.asteca.servicio.ServicioException;

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
	
	@Override
	public List<MateriaRegistroDTO> findByNombre(String nombre) throws FachadaException{
		try{
			List<MateriaRegistroDTO> lista = servicioMateriaReg.findByNombre(nombre);
			return lista;
		}catch(ServicioException e){
			throw new FachadaException("Error en findByNombre: "+e.getMessage(), e);
		}
	}

}
