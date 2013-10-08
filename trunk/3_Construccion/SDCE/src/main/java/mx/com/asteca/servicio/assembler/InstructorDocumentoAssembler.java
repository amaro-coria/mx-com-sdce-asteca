/**
 * 
 */
package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.DocumentoDTO;
import mx.com.asteca.comun.dto.InstructorDocumentoDTO;
import mx.com.asteca.persistencia.entidades.Docs;
import mx.com.asteca.persistencia.entidades.Instructores;
import mx.com.asteca.persistencia.entidades.InstructoresDocumentos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 * 
 */
@Component(Constantes.ASSEMBLER_INSTRUCTORES_DOCUMENTOS)
public class InstructorDocumentoAssembler extends
		Assembler<InstructorDocumentoDTO, InstructoresDocumentos> {

	@Autowired
	@Qualifier(Constantes.ASSEMBLER_DOCS)
	private Assembler assemblerDocs;

	@Override
	public InstructorDocumentoDTO getDTOTransform(InstructoresDocumentos mapping) {
		if (mapping == null) {
			return null;
		}
		InstructorDocumentoDTO dto = new InstructorDocumentoDTO();
		dto.setId(mapping.getIdInstructorDocumento());
		dto.setIdInstructor(mapping.getInstructores().getIdInstructor());
		dto.setDocumento((DocumentoDTO) assemblerDocs.getDTOTransform(mapping
				.getDocumento()));
		return dto;
	}

	@Override
	public InstructoresDocumentos getMappingTransform(InstructorDocumentoDTO dto) {
		if (dto == null) {
			return null;
		}
		InstructoresDocumentos mapping = new InstructoresDocumentos();
		if (dto.getId() != 0) {
			mapping.setIdInstructorDocumento(dto.getId());
		}
		if (dto.getIdInstructor() != 0) {
			Instructores instructores = new Instructores();
			instructores.setIdInstructor(dto.getIdInstructor());
			mapping.setInstructores(instructores);
		}
		mapping.setDocumento((Docs) assemblerDocs.getMappingTransform(dto
				.getDocumento()));
		return mapping;
	}

}
