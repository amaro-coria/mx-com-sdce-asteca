package mx.com.asteca.servicio;

import mx.com.asteca.comun.dto.PersonaDTO;
import mx.com.asteca.persistencia.entidades.Personas;

public interface PersonaServicio extends
		BaseService<PersonaDTO, Integer, Personas> {
	
	PersonaDTO findByUserName(String userName) throws ServicioException;
}
