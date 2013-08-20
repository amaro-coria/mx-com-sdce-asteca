package mx.com.asteca.fachada.impl;

import java.util.List;

import mx.com.asteca.comun.dto.ClienteDTO;
import mx.com.asteca.fachada.ClienteFachada;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.ClienteServicio;
import mx.com.asteca.servicio.ServicioException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteFachadaImpl extends BaseFachadaImpl<ClienteDTO, Integer> implements ClienteFachada{

	@Autowired
	private ClienteServicio clienteServicio;
	
	@Override
	BaseService getBaseService() {
		return clienteServicio;
	}

	

}
