/**
 * 
 */
package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.DocumentoDTO;
import mx.com.asteca.persistencia.entidades.Docs;
import mx.com.asteca.persistencia.entidades.Estatus;

import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 *
 */
@Component(Constantes.ASSEMBLER_DOCS)
public class DocsAssembler extends Assembler<DocumentoDTO, Docs> {

	@Override
	public DocumentoDTO getDTOTransform(Docs mapping) {
		if(mapping == null){
			return null;
		}
		DocumentoDTO dto = new DocumentoDTO();
			if(mapping.getEstatus() != null){
				dto.setEstatus(mapping.getEstatus().getDescEstatus());
				dto.setIdEstatus(mapping.getEstatus().getIdEstatus());
			}
			dto.setIdAlumno(mapping.getIdAlumno());
			dto.setNombre(mapping.getNombre());
			dto.setRuta(mapping.getRuta());
			dto.setIdDoc(mapping.getIdDoc());
		return dto;
	}

	@Override
	public Docs getMappingTransform(DocumentoDTO dto) {
		if(dto == null){
			return null;
		}
		Docs mapping = new Docs();
			mapping.setNombre(dto.getNombre());
			mapping.setRuta(dto.getRuta());
			if(dto.getIdEstatus() != 0){
				Estatus estatus = new Estatus();
				estatus.setIdEstatus((short) dto.getIdEstatus());
				mapping.setEstatus(estatus);
			}
			if(dto.getIdAlumno() != 0){
				mapping.setIdAlumno(dto.getIdAlumno());
			}
			if(dto.getIdDoc() != 0){
				mapping.setIdDoc(dto.getIdDoc());
			}
		return mapping;
	}

}
