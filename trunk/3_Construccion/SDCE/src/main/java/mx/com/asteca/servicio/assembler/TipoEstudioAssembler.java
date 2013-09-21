/**
 * 
 */
package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.TipoEstudioDTO;
import mx.com.asteca.persistencia.entidades.TiposEstudios;

import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 *
 */
@Component(Constantes.ASSEMBLER_TIPO_ESTUDIO)
public class TipoEstudioAssembler extends
		Assembler<TipoEstudioDTO, TiposEstudios> {

	/* (non-Javadoc)
	 * @see mx.com.asteca.servicio.assembler.Assembler#getDTOTransform(java.lang.Object)
	 */
	@Override
	public TipoEstudioDTO getDTOTransform(TiposEstudios mapping) {
		if(mapping == null){
			return null;
		}
		TipoEstudioDTO dto = new TipoEstudioDTO();
			dto.setIdTipoEstudios(mapping.getIdTipoEstudios());
			dto.setNombre(mapping.getNombre());
		return dto;
	}

	/* (non-Javadoc)
	 * @see mx.com.asteca.servicio.assembler.Assembler#getMappingTransform(java.lang.Object)
	 */
	@Override
	public TiposEstudios getMappingTransform(TipoEstudioDTO dto) {
		if(dto == null){
			return null;
		}
		TiposEstudios mapping = new TiposEstudios();
			mapping.setNombre(dto.getNombre());
			if(dto.getIdTipoEstudios() != 0){
				mapping.setIdTipoEstudios(dto.getIdTipoEstudios());
			}
		return mapping;
	}

}
