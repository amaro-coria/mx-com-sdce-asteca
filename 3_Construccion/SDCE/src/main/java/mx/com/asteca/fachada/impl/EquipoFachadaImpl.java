package mx.com.asteca.fachada.impl;

import mx.com.asteca.comun.dto.EquipoDTO;
import mx.com.asteca.fachada.EquipoFachada;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.EquipoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EquipoFachadaImpl extends BaseFachadaImpl<EquipoDTO, Integer> implements EquipoFachada{

	@Autowired
	private EquipoServicio equipoServicio;
	
	@Override
	BaseService getBaseService() {
		return equipoServicio;
	}

	public Integer saveEquipo(EquipoDTO Equipos) throws FachadaException {
		return null;
	}
}
