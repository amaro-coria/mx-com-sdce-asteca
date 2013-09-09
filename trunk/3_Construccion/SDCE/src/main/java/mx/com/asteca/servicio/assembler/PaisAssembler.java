package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.PaisDTO;
import mx.com.asteca.persistencia.entidades.Paises;

import org.springframework.stereotype.Component;

@Component(Constantes.ASSEMBLER_PAISES)
public class PaisAssembler extends Assembler<PaisDTO, Paises> {

	@Override
	public PaisDTO getDTOTransform(Paises mapping) {
		if(mapping == null){
			return null;
		}
		PaisDTO dto = new PaisDTO();
			dto.setActivo(mapping.getActivo());
			dto.setIdPais(mapping.getIdPais());
			dto.setNombrePais(mapping.getNombrePais());
		return dto;
	}

	@Override
	public Paises getMappingTransform(PaisDTO dto) {
		if(dto == null){
			return null;
		}
		Paises mapping = new Paises();
			mapping.setActivo(dto.getActivo());
			mapping.setNombrePais(dto.getNombrePais());
			if(dto.getIdPais() != 0){
				mapping.setIdPais(dto.getIdPais());
			}
		return mapping;
	}

}
