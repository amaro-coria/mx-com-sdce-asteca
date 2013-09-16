package mx.com.asteca.fachada.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.com.asteca.comun.dto.PersonaDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.PersonaFachada;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.PersonaServicio;
import mx.com.asteca.servicio.ServicioException;

@Component
public class PersonaFachadaImpl extends BaseFachadaImpl<PersonaDTO, Integer>
		implements PersonaFachada {
	
	@Autowired
	private PersonaServicio personaServicio;

	@Override
	public PersonaDTO getUser(String user) throws FachadaException {
		try {
			PersonaDTO persona = personaServicio.findByUserName(user);
			
			return persona;
		} catch (ServicioException e) {
			throw new FachadaException("Excepcion obteniendo usuario",e);
		}
	}

	@Override
	BaseService getBaseService() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
