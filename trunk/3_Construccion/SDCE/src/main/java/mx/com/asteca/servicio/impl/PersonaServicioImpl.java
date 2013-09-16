package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.NotificacionDTO;
import mx.com.asteca.comun.dto.PersonaDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.NotificacionDAO;
import mx.com.asteca.persistencia.dao.PersonaDAO;
import mx.com.asteca.persistencia.entidades.Notificaciones;
import mx.com.asteca.persistencia.entidades.Personas;
import mx.com.asteca.servicio.PersonaServicio;
import mx.com.asteca.servicio.ServicioException;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaServicioImpl extends
		BaseServiceImpl<PersonaDTO, Integer, Personas> implements
		PersonaServicio {

	@Autowired
	private PersonaDAO personaDAO;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_PERSONA)
	private Assembler assemblerPersona;
	
	@Override
	BaseDAO getDAO() {
		return personaDAO;
	}

	@Override
	Assembler getAssembler() {
		return assemblerPersona;
	}

	@Override
	@Transactional(readOnly = true)
	public PersonaDTO findByUserName(String userName) throws ServicioException {
		try {
			Personas usuario = personaDAO.findByUserName(userName);
			PersonaDTO usuarioDTO = (PersonaDTO) assemblerPersona
					.getDTOTransform(usuario);
			
			return usuarioDTO;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServicioException(e.getMessage(),e);
		}
	}

}
