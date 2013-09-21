/**
 * 
 */
package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.DomicilioDTO;
import mx.com.asteca.comun.dto.PersonaDTO;
import mx.com.asteca.comun.dto.ReferenciaDTO;
import mx.com.asteca.persistencia.entidades.Alumnos;
import mx.com.asteca.persistencia.entidades.Domicilios;
import mx.com.asteca.persistencia.entidades.Personas;
import mx.com.asteca.persistencia.entidades.Referencias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 *
 */
@Component(Constantes.ASSEMBLER_REFERENCIAS)
public class ReferenciaAssembler extends Assembler<ReferenciaDTO, Referencias> {

	@Autowired
	@Qualifier(Constantes.ASSEMBLER_DOMICILIO)
	private Assembler assemblerDomicilio;
	
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_PERSONA)
	private Assembler assemblerPersona;
	
	/* (non-Javadoc)
	 * @see mx.com.asteca.servicio.assembler.Assembler#getDTOTransform(java.lang.Object)
	 */
	@Override
	public ReferenciaDTO getDTOTransform(Referencias mapping) {
		if(mapping == null){
			return null;
		}
		
		ReferenciaDTO dto = new ReferenciaDTO();
			if(mapping.getDomicilios() != null){
				DomicilioDTO dom = (DomicilioDTO) assemblerDomicilio.getDTOTransform(mapping.getDomicilios());
				dto.setDtoDomicilio(dom);
			}
			if(mapping.getPersonas() != null){
				PersonaDTO per = (PersonaDTO) assemblerPersona.getDTOTransform(mapping.getPersonas());
				dto.setDtoPersona(per);
			}
			if(mapping.getAlumnos() != null){
				dto.setIdAlumno(mapping.getAlumnos().getIdAlumno());
			}
			dto.setIdReferencia(mapping.getIdReferencia());
		return dto;
	}

	@Override
	public Referencias getMappingTransform(ReferenciaDTO dto) {
		if(dto == null){
			return null;
		}
		Referencias mapping = new Referencias();
		if(dto.getDtoDomicilio() != null){
			Domicilios dom = (Domicilios) assemblerDomicilio.getMappingTransform(dto.getDtoDomicilio());
			mapping.setDomicilios(dom);
		}
		if(dto.getDtoPersona() != null){
			Personas per = (Personas) assemblerPersona.getMappingTransform(dto.getDtoPersona());
			mapping.setPersonas(per);
		}
		if(dto.getIdAlumno() != 0){
			Alumnos alumnos = new Alumnos(dto.getIdAlumno());
			mapping.setAlumnos(alumnos);
		}
		if(dto.getIdReferencia() != 0){
			mapping.setIdReferencia(dto.getIdReferencia());
		}
		return mapping;
		
	}

}
