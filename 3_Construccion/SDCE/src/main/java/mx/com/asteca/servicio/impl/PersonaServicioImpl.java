package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.PersonaDTO;
import mx.com.asteca.comun.dto.PersonaDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.PersonaDAO;
import mx.com.asteca.persistencia.entidades.Personas;
import mx.com.asteca.servicio.PersonaServicio;
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
		BaseServiceImpl<PersonaDTO, Integer, Personas> implements PersonaServicio{

	@Autowired
	private PersonaDAO daoPersona;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_PERSONA)
	private Assembler assembler;
	
	@Override
	BaseDAO getDAO() {
		return daoPersona;
	}

	@Override
	Assembler getAssembler() {
		return assembler;
	}

	@Override
	@Transactional(readOnly = true)
	public PersonaDTO findByUserName(String userName) throws ServicioException {
		try {
			Personas usuario = daoPersona.findByUserName(userName);
			PersonaDTO usuarioDTO = (PersonaDTO) assembler
					.getDTOTransform(usuario);
			
			return usuarioDTO;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServicioException(e.getMessage(),e);
		}
	}

}
