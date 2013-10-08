/**
 * 
 */
package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.AsentamientoDTO;
import mx.com.asteca.persistencia.entidades.Asentamientos;
import mx.com.asteca.persistencia.entidades.AsentamientosId;

import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 *
 */
@Component(Constantes.ASSEMBLER_ASENTAMIENTOS)
public class AsentamientoAssembler extends Assembler<AsentamientoDTO, Asentamientos> {

	@Override
	public AsentamientoDTO getDTOTransform(Asentamientos mapping) {
		if(mapping == null){
			return null;
		}
		AsentamientoDTO dto = new AsentamientoDTO();
			dto.setActivo((short) (mapping.getActivo() == null ? 0 : mapping.getActivo()));
			dto.setClave(mapping.getClave());
			dto.setIdCp(mapping.getIdCp());
			dto.setNombre(mapping.getNombre());
			dto.setIdAsentamiento(mapping.getId().getIdAsentamiento());
			dto.setIdEstado(mapping.getId().getIdEstado());
			dto.setIdMunicipio(mapping.getId().getIdMunicipio());
		return dto;
	}

	@Override
	public Asentamientos getMappingTransform(AsentamientoDTO dto) {
		if(dto == null){
			return null;
		}
		Asentamientos mapping = new Asentamientos();
			mapping.setActivo(dto.getActivo());
			mapping.setClave(dto.getClave());
			mapping.setIdCp(dto.getIdCp());
			mapping.setNombre(dto.getNombre());
			if(dto.getIdEstado() != 0 && dto.getIdMunicipio() != 0 && dto.getIdAsentamiento() != 0){
				AsentamientosId id = new AsentamientosId();
					id.setIdAsentamiento(dto.getIdAsentamiento());
					id.setIdEstado(dto.getIdEstado());
					id.setIdMunicipio(dto.getIdMunicipio());
				mapping.setId(id);
			}
		return mapping;
	}

}
