package mx.com.asteca.fachada.impl;

import java.util.List;

import mx.com.asteca.comun.dto.EquipoDTO;
import mx.com.asteca.fachada.EquipoFachada;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.EquipoServicio;
import mx.com.asteca.servicio.ServicioException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EquipoFachadaImpl extends BaseFachadaImpl<EquipoDTO, Integer> implements EquipoFachada{

//	@Autowired
//	private EquipoServicio equipoServicio;
	
	@Override
	BaseService getBaseService() {
//		return equipoServicio;
		return null;
	}

	public Integer saveEquipo(EquipoDTO Equipos) throws FachadaException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<EquipoDTO> getAllEquipo() throws FachadaException {
		List<EquipoDTO> resp = null;
//		try {
//			 resp = equipoServicio.getAll();
//		} catch (ServicioException e) {
//			e.printStackTrace();
//		}
		
		return resp;
	}

	

	

}
