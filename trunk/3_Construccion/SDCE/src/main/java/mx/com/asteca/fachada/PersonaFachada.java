package mx.com.asteca.fachada;

import mx.com.asteca.comun.dto.PersonaDTO;

public interface PersonaFachada extends BaseFachada<PersonaDTO, Integer> {
	
	PersonaDTO getUser(String user) throws FachadaException;

}
