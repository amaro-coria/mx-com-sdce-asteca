/**
 * 
 */
package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.TipoInstructorDTO;
import mx.com.asteca.persistencia.entidades.TiposInstructores;

import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 * 
 */
@Component(Constantes.ASSEMBLER_TIPOS_INSTRUCTORES)
public class TipoInstructorAssembler extends
		Assembler<TipoInstructorDTO, TiposInstructores> {

	@Override
	public TipoInstructorDTO getDTOTransform(TiposInstructores mapping) {
		if (mapping == null) {
			return null;
		}
		TipoInstructorDTO dto = new TipoInstructorDTO();
		dto.setIdTipoInstructor(mapping.getIdTipoInstructor());
		dto.setNombre(mapping.getNombre());
		return dto;
	}

	@Override
	public TiposInstructores getMappingTransform(TipoInstructorDTO dto) {
		if (dto == null) {
			return null;
		}
		TiposInstructores mapping = new TiposInstructores();
		mapping.setNombre(dto.getNombre());
		if (dto.getIdTipoInstructor() != 0) {
			mapping.setIdTipoInstructor(dto.getIdTipoInstructor());
		}
		return mapping;
	}

}
