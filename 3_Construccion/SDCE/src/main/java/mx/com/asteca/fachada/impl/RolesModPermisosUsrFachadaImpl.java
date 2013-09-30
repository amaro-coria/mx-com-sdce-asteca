package mx.com.asteca.fachada.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.com.asteca.comun.dto.RolesModPermisosUsrDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.RolesModPermisosUsrFachada;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.RolesModPermisosUsrServicio;

@Component
public class RolesModPermisosUsrFachadaImpl extends
		BaseFachadaImpl<RolesModPermisosUsrDTO, Integer> implements
		RolesModPermisosUsrFachada {

	@Autowired
	private RolesModPermisosUsrServicio servicio;
	@Override
	BaseService getBaseService() {
		return servicio;
	}
	@Override
	public List<RolesModPermisosUsrDTO> buscarPorRolModUsuario(
			int idRolModUsuarios) {
		return servicio.buscarPorRolModUsuario(idRolModUsuarios);
	}
	
	

}
