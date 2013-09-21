package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.AulaDTO;
import mx.com.asteca.persistencia.entidades.Aulas;
import mx.com.asteca.persistencia.entidades.CatGral;

import org.springframework.stereotype.Component;

/**
 * @author Jorge Amaro Coria
 * @version 1.0
 * @since 1.0
 *
 */
@Component(Constantes.ASSEMBLER_AULAS)
public class AulaAssembler extends Assembler<AulaDTO, Aulas>{

	/* (non-Javadoc)
	 * @see mx.com.asteca.servicio.assembler.Assembler#getDTOTransform(java.lang.Object)
	 */
	@Override
	public AulaDTO getDTOTransform(Aulas mapping) {
		if(mapping == null){
			return null;
		}
		AulaDTO dto = new AulaDTO();
			dto.setActivo(mapping.getActivo());
			dto.setCapacidad(mapping.getCapacidad());
			dto.setClave(mapping.getClave());
			dto.setDsc(mapping.getDsc());
			dto.setIdAula(mapping.getIdAula());
			dto.setIdSede(mapping.getCatGral().getIdCatGral());
			dto.setSede(mapping.getCatGral().getDsc());
		return dto;
	}

	/* (non-Javadoc)
	 * @see mx.com.asteca.servicio.assembler.Assembler#getMappingTransform(java.lang.Object)
	 */
	@Override
	public Aulas getMappingTransform(AulaDTO dto) {
		if(dto == null){
			return null;
		}
		Aulas mapping = new Aulas();
			mapping.setActivo(dto.getActivo());
			mapping.setCapacidad(dto.getCapacidad());
			mapping.setClave(dto.getClave());
			mapping.setDsc(dto.getDsc());
			if(dto.getIdAula() != 0){
				mapping.setIdAula(dto.getIdAula());
			}
			CatGral catGral = new CatGral();
				catGral.setIdCatGral(dto.getIdSede());
			mapping.setCatGral(catGral);
		return mapping;
	}

}
