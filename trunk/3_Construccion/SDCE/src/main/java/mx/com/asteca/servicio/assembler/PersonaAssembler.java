package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.PersonaDTO;
import mx.com.asteca.persistencia.entidades.Personas;

import org.springframework.stereotype.Component;

@Component(Constantes.ASSEMBLER_PERSONA)
public class PersonaAssembler extends Assembler<PersonaDTO, Personas> {

	@Override
	public PersonaDTO getDTOTransform(Personas mapping) {
		if(mapping == null){
			return null;
		}
		PersonaDTO dto = new PersonaDTO();
			dto.setApellidoM(mapping.getApellidoM());
			dto.setApellidoP(mapping.getApellidoP());
			dto.setCurp(mapping.getCurp());
			dto.setEmail(mapping.getEmail());
			dto.setFechaNac(mapping.getFechaNac());
			dto.setIfe(mapping.getIfe());
			dto.setLugarNac(mapping.getLugarNac());
			dto.setNombre(mapping.getNombre());
			dto.setPasaporte(mapping.getPasaporte());
			dto.setRfc(mapping.getRfc());
			dto.setIdPersona(mapping.getIdPersona());
			dto.setUsuario(mapping.getUsuario());
			dto.setPassword(mapping.getPassword());
		return dto;
	}

	@Override
	public Personas getMappingTransform(PersonaDTO dto) {
		if(dto == null){
			return null;
		}
		Personas mapping = new Personas();
		mapping.setApellidoM(dto.getApellidoM());
		mapping.setApellidoP(dto.getApellidoP());
		mapping.setCurp(dto.getCurp());
		mapping.setEmail(dto.getEmail());
		mapping.setFechaNac(dto.getFechaNac());
		mapping.setIfe(dto.getIfe());
		mapping.setLugarNac(dto.getLugarNac());
		mapping.setNombre(dto.getNombre());
		mapping.setPasaporte(dto.getPasaporte());
		mapping.setRfc(dto.getRfc());
		mapping.setUsuario(dto.getUsuario());
		mapping.setPassword(dto.getPassword());
		if(dto.getIdPersona() != 0){
			mapping.setIdPersona(dto.getIdPersona());
		}
	return mapping;
	}

}
