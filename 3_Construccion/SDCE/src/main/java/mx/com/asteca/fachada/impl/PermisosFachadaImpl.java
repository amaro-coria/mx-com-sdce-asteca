package mx.com.asteca.fachada.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.com.asteca.comun.dto.PermisosDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.PermisosFachada;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.PermisosServicio;

@Component
public class PermisosFachadaImpl extends BaseFachadaImpl<PermisosDTO, Integer>
		implements PermisosFachada {
	
	@Autowired
	private PermisosServicio servicio;

	@Override
	BaseService getBaseService() {
		return servicio;
	}

}
