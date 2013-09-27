package mx.com.asteca.fachada.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.com.asteca.comun.dto.RolesDTO;
import mx.com.asteca.fachada.RolesFachada;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.RolesServicio;

@Component
public class RolesFachadaImpl extends BaseFachadaImpl<RolesDTO, Integer>
implements RolesFachada {

	@Autowired
	private RolesServicio servicio;
	
	@Override
	BaseService getBaseService() {
		// TODO Auto-generated method stub
		return servicio;
	}

}
