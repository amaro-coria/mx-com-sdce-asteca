/**
 * 
 */
package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.EstudioDTO;
import mx.com.asteca.persistencia.entidades.Estudios;
import mx.com.asteca.persistencia.entidades.TiposEstudios;

import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 *
 */
@Component(Constantes.ASSEMBLER_ESTUDIO)
public class EstudioAssembler extends Assembler<EstudioDTO, Estudios> {

	/* (non-Javadoc)
	 * @see mx.com.asteca.servicio.assembler.Assembler#getDTOTransform(java.lang.Object)
	 */
	@Override
	public EstudioDTO getDTOTransform(Estudios mapping) {
		if(mapping == null){
			return null;
		}
		EstudioDTO dto = new EstudioDTO();
			dto.setCert(mapping.getCert());
			dto.setFechaFin(mapping.getFechaFin());
			dto.setFechaIni(mapping.getFechaIni());
			dto.setIdEstudios(mapping.getIdEstudios());//llave PK
			if(mapping.getTiposEstudios() != null){
				dto.setIdTipoEstudio(mapping.getTiposEstudios().getIdTipoEstudios());
				dto.setTipoEstudio(mapping.getTiposEstudios().getNombre());
			}
			dto.setInstitucion(mapping.getInstitucion());
		return dto;
	}

	/* (non-Javadoc)
	 * @see mx.com.asteca.servicio.assembler.Assembler#getMappingTransform(java.lang.Object)
	 */
	@Override
	public Estudios getMappingTransform(EstudioDTO dto) {
		if(dto == null){
			return null;
		}
		Estudios mapping = new Estudios();
			mapping.setCert(dto.getCert());
			mapping.setFechaFin(dto.getFechaFin());
			mapping.setFechaIni(dto.getFechaIni());
			if(dto.getIdEstudios() != 0){
				mapping.setIdEstudios(dto.getIdEstudios()); //llave PK
			}
			mapping.setInstitucion(dto.getInstitucion());
			if(dto.getIdTipoEstudio() != 0){
				TiposEstudios tiposEstudios = new TiposEstudios();
				tiposEstudios.setIdTipoEstudios((short) dto.getIdTipoEstudio());
				mapping.setTiposEstudios(tiposEstudios);
			}
		return mapping;
	}

}
