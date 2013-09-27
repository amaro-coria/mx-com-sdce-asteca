package mx.com.asteca.fachada.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.com.asteca.comun.dto.RolesModulosDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.RolesModulosFachada;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.RolesModulosServicio;

@Component
public class RolesModulosFachadaImpl extends
		BaseFachadaImpl<RolesModulosDTO, Integer> implements
		RolesModulosFachada {

	@Autowired
	private RolesModulosServicio servicio;
	
	@Override
	BaseService getBaseService() {
		// TODO Auto-generated method stub
		return servicio;
	}

	@Override
	public List<RolesModulosDTO> buscarPorRol(int idRol) {
		return servicio.buscarPorRol(idRol);
	}

}
