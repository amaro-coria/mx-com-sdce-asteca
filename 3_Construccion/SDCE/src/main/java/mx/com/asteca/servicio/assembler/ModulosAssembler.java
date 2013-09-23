package mx.com.asteca.servicio.assembler;

import org.springframework.stereotype.Component;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.ModulosDTO;
import mx.com.asteca.persistencia.entidades.Modulos;

@Component(Constantes.ASSEMBLER_MODULOS)
public class ModulosAssembler extends Assembler<ModulosDTO, Modulos> {

	@Override
	public ModulosDTO getDTOTransform(Modulos mapping) {
		if(mapping == null) {
			return null;
		}
		ModulosDTO dto = new ModulosDTO();
		
		dto.setIdModulo(mapping.getIdModulo());
		dto.setNombre(mapping.getNombre());
		dto.setDsc(mapping.getDsc());
		
		return dto;
	}

	@Override
	public Modulos getMappingTransform(ModulosDTO dto) {
		if(dto == null) {
			return null;
		}
		Modulos mapping = new Modulos();
		
		mapping.setIdModulo(dto.getIdModulo());
		mapping.setNombre(dto.getNombre());
		mapping.setDsc(dto.getDsc());
		
		return mapping;
	}

}
