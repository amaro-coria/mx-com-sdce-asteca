package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.ModuloDTO;
import mx.com.asteca.persistencia.entidades.Modulos;

import org.springframework.stereotype.Component;

@Component(Constantes.ASSEMBLER_MODULO)
public class ModuloAssembler extends Assembler<ModuloDTO, Modulos> {

	@Override
	public ModuloDTO getDTOTransform(Modulos mapping) {
		if (mapping == null) {
			return null;
		}
		ModuloDTO dto = new ModuloDTO();
		dto.setDsc(mapping.getDsc());
		dto.setIdModulo(mapping.getIdModulo());
		dto.setNombre(mapping.getNombre());
		return dto;
	}

	@Override
	public Modulos getMappingTransform(ModuloDTO dto) {
		if (dto == null) {
			return null;
		}
		Modulos mapping = new Modulos();
		mapping.setDsc(dto.getDsc());
		mapping.setNombre(dto.getNombre());
		if (dto.getIdModulo() != 0) {
			mapping.setIdModulo(dto.getIdModulo());
		}
		return mapping;
	}

}
