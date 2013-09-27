package mx.com.asteca.fachada.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.com.asteca.comun.dto.RolesModPermisosSobreDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.RolesModPermisosSobreFachada;
import mx.com.asteca.persistencia.dao.RolesModPermisosSobreDAO;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.RolesModPermisosSobreServicio;

@Component
public class RolesModPermisosSobreFachadaImpl extends
		BaseFachadaImpl<RolesModPermisosSobreDTO, Integer> implements
		RolesModPermisosSobreFachada {
	
	@Autowired
	private RolesModPermisosSobreServicio servicio;
	
	@Override
	BaseService getBaseService() {
		// TODO Auto-generated method stub
		return servicio;
	}

	
}
