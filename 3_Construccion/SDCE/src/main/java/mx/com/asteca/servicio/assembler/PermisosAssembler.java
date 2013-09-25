package mx.com.asteca.servicio.assembler;

import org.springframework.stereotype.Component;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.PermisosDTO;
import mx.com.asteca.persistencia.entidades.Permisos;

@Component(Constantes.ASSEMBLER_PERMISOS)
public class PermisosAssembler extends Assembler<PermisosDTO, Permisos> {

	@Override
	public PermisosDTO getDTOTransform(Permisos mapping) {
		if(mapping == null){
			return null;
		}
		PermisosDTO dto = new PermisosDTO();
		dto.setIdPermiso(mapping.getIdPermiso());
		dto.setAlta(mapping.getAlta());
		dto.setBorrar(mapping.getBorrar());
		dto.setCambios(mapping.getCambios());
		dto.setConsulta(mapping.getConsulta());
		dto.setImpresion(mapping.getImpresion());
		
		return dto;
	}

	@Override
	public Permisos getMappingTransform(PermisosDTO dto) {
		if(dto == null) {
			return null;
		}
		Permisos mapping = new Permisos();
		mapping.setIdPermiso(dto.getIdPermiso());
		mapping.setAlta(dto.getAlta());
		mapping.setBorrar(dto.getBorrar());
		mapping.setCambios(dto.getCambios());
		mapping.setConsulta(dto.getConsulta());
		mapping.setImpresion(dto.getImpresion());
		
		return mapping;
	}

}
