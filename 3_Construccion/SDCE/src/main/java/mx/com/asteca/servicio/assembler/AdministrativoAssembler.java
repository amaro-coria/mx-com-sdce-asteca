/**
 * 
 */
package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.AdministrativoDTO;
import mx.com.asteca.comun.dto.DomicilioDTO;
import mx.com.asteca.comun.dto.PersonaDTO;
import mx.com.asteca.persistencia.entidades.Administrativos;
import mx.com.asteca.persistencia.entidades.Domicilios;
import mx.com.asteca.persistencia.entidades.Personas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 * 
 */
@Component(Constantes.ASSEMBLER_ADMINISTRATIVOS)
public class AdministrativoAssembler extends
		Assembler<AdministrativoDTO, Administrativos> {

	@Autowired
	@Qualifier(Constantes.ASSEMBLER_PERSONA)
	private Assembler assemblerPersona;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_DOMICILIO)
	private Assembler assemblerDomicilio;

	@Override
	public AdministrativoDTO getDTOTransform(Administrativos mapping) {
		if (mapping == null) {
			return null;
		}
		AdministrativoDTO dto = new AdministrativoDTO();
		dto.setIdAmin(mapping.getIdAdmin());
		dto.setNoEmpleado(mapping.getNoEmpleado());
		PersonaDTO dtoPersona = (PersonaDTO) assemblerPersona
				.getDTOTransform(mapping.getPersonas());
		DomicilioDTO dtoDomicilio = (DomicilioDTO) assemblerDomicilio
				.getDTOTransform(mapping.getDomicilios());
		dto.setDtoDomicilio(dtoDomicilio);
		dto.setDtoPersona(dtoPersona);
		if(dtoPersona != null){
			dto.setNombreCompleto(dtoPersona.getNombre() + " " +  dtoPersona.getApellidoP() + " "+ dtoPersona.getApellidoM());
		}
		return dto;
	}

	@Override
	public Administrativos getMappingTransform(AdministrativoDTO dto) {
		if (dto == null) {
			return null;
		}
		Administrativos mapping = new Administrativos();
		mapping.setNoEmpleado(dto.getNoEmpleado());
		if (dto.getIdAmin() != 0) {
			mapping.setIdAdmin(dto.getIdAmin());
		}
		Personas personaMappin = (Personas) assemblerPersona
				.getMappingTransform(dto.getDtoPersona());
		Domicilios domicilioMapping = (Domicilios) assemblerDomicilio
				.getMappingTransform(dto.getDtoDomicilio());
		mapping.setPersonas(personaMappin);
		mapping.setDomicilios(domicilioMapping);
		return mapping;
	}

}
