package mx.com.asteca.fachada.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.com.asteca.comun.dto.ModulosDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.ModulosFachada;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.ModulosServicio;

@Component
public class ModulosFachadaImpl extends BaseFachadaImpl<ModulosDTO, Integer>
		implements ModulosFachada {
	
	@Autowired
	private ModulosServicio servicio;
	
	@Override
	BaseService getBaseService() {
		return servicio;
	}
}
