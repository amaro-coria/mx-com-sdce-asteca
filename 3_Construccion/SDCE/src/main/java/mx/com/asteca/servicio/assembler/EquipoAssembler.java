package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.EquipoDTO;
import mx.com.asteca.persistencia.entidades.CatGral;
import mx.com.asteca.persistencia.entidades.Equipos;

import org.springframework.stereotype.Component;

@Component(Constantes.ASSEMBLER_EQUIPOS)
public class EquipoAssembler extends Assembler<EquipoDTO, Equipos>{

	@Override
	public EquipoDTO getDTOTransform(Equipos mapping) {
		if(mapping == null || mapping.getIdEquipo() == 0){
			return null;
		}
		EquipoDTO dto = new EquipoDTO();
		dto.setActivo(mapping.getActivo());
		dto.setClave(mapping.getClave());
		dto.setIdEquipo(mapping.getIdEquipo());
		dto.setDsc(mapping.getDsc());
		
		CatGral catGral = mapping.getCatGral();
		dto.setIdCatGralDTO(catGral == null  ? null : catGral.getIdCatGral());
		dto.setDescCatGral(catGral == null  ? null : catGral.getDsc());
		
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
		mapping.setIdEquipo(dto.getIdEquipo());
		mapping.setDsc(dto.getDsc());

		if(dto.getIdCatGral() != 0){
			CatGral catGral = new CatGral((short) dto.getIdCatGral());
			mapping.setCatGral(catGral);
		}

		return mapping;
	}

}
