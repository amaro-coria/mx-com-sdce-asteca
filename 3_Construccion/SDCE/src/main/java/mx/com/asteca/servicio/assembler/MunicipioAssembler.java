/**
 * 
 */
package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.MunicipioDTO;
import mx.com.asteca.persistencia.entidades.Municipios;
import mx.com.asteca.persistencia.entidades.MunicipiosId;

import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 * 
 */
@Component(Constantes.ASSEMBLER_MUNICIPIOS)
public class MunicipioAssembler extends Assembler<MunicipioDTO, Municipios> {

	@Override
	public MunicipioDTO getDTOTransform(Municipios mapping) {
		if(mapping == null){
			return null;
		}
		MunicipioDTO dto = new MunicipioDTO();
			dto.setActivo(mapping.getActivo());
			dto.setClave(mapping.getClave());
			dto.setNombre(mapping.getNombre());
			dto.setIdMunicipio(mapping.getId().getIdMunicipio());
			dto.setIdEstado(mapping.getId().getIdEstado());
		return dto;
	}

	@Override
	public Municipios getMappingTransform(MunicipioDTO dto) {
		if (dto == null) {
			return null;
		}
		Municipios mapping = new Municipios();
			mapping.setActivo(dto.getActivo());
			mapping.setClave(dto.getClave());
			mapping.setNombre(dto.getNombre());
			if(dto.getIdMunicipio() != 0 && dto.getIdEstado() != 0){
				MunicipiosId id = new MunicipiosId();
				id.setIdEstado(dto.getIdEstado());
				id.setIdMunicipio(dto.getIdMunicipio());
				mapping.setId(id);
			}
		return mapping;
	}

}
