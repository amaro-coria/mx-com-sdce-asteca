package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.EquipoDTO;
import mx.com.asteca.persistencia.entidades.CatGral;
import mx.com.asteca.persistencia.entidades.Equipos;

import org.springframework.stereotype.Component;

@Component(Constantes.ASSEMBLER_EQUIPO)
public class EquipoAssembler extends Assembler<EquipoDTO, Equipos> {

	@Override
	public EquipoDTO getDTOTransform(Equipos mapping) {
		if(mapping == null){
			return null;
		}
		EquipoDTO dto = new EquipoDTO();
			dto.setActivo(mapping.getActivo());
			dto.setClave(mapping.getClave());
			dto.setDsc(mapping.getDsc());
			dto.setIdEquipo(mapping.getIdEquipo());
			dto.setDscTipo(mapping.getCatGral().getDsc());
			dto.setIdTipo(mapping.getCatGral().getIdCatGral());
		return dto;
	}

	@Override
	public Equipos getMappingTransform(EquipoDTO dto) {
		if(dto == null){
			return null;
		}
		Equipos mapping = new Equipos();
			mapping.setActivo(dto.getActivo());
			mapping.setClave(dto.getClave());
			mapping.setDsc(dto.getDsc());
			if(dto.getIdEquipo() != 0){
				mapping.setIdEquipo(dto.getIdEquipo());
			}
			if(dto.getIdTipo() != 0){
				CatGral catGral = new CatGral();
				catGral.setIdCatGral(dto.getIdTipo());
				mapping.setCatGral(catGral);
			}
		return mapping;
	}

}
