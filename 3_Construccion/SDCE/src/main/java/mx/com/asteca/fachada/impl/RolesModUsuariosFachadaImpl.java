package mx.com.asteca.fachada.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.com.asteca.comun.dto.RolesModUsuariosDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.RolesModUsuariosFachada;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.RolesModUsuariosServicio;

@Component
public class RolesModUsuariosFachadaImpl extends
		BaseFachadaImpl<RolesModUsuariosDTO, Integer> implements
		RolesModUsuariosFachada {

	@Autowired
	private RolesModUsuariosServicio servicio;
	
	@Override
	BaseService getBaseService() {
		// TODO Auto-generated method stub
		return servicio;
	}

}
