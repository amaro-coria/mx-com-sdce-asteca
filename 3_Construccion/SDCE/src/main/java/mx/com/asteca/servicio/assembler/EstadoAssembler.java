/**
 * 
 */
package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.EstadoDTO;
import mx.com.asteca.persistencia.entidades.Estados;
import mx.com.asteca.persistencia.entidades.Paises;

import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 *
 */
@Component(Constantes.ASSEMBLER_ESTADOS)
public class EstadoAssembler extends Assembler<EstadoDTO, Estados> {

	@Override
	public EstadoDTO getDTOTransform(Estados mapping) {
		if(mapping == null){
			return null;
		}
		EstadoDTO dto = new EstadoDTO();
			dto.setActivo(mapping.getActivo());
			dto.setClave(mapping.getClave());
			dto.setIdEstado(mapping.getIdEstado());
			dto.setNombre(mapping.getNombre());
			dto.setIdPais(mapping.getPaises().getIdPais());
			dto.setPais(mapping.getPaises().getNombrePais());
		return dto;
	}

	@Override
	public Estados getMappingTransform(EstadoDTO dto) {
		if(dto == null){
			return null;
		}
		Estados mapping = new Estados();
			mapping.setActivo(dto.getActivo());
			mapping.setClave(dto.getClave());
			mapping.setNombre(dto.getNombre());
			if(dto.getIdEstado() != 0){
				mapping.setIdEstado(dto.getIdEstado());
			}
			if(dto.getIdPais() != 0 ){
				Paises pais = new Paises();
				pais.setIdPais(dto.getIdPais());
				mapping.setPaises(pais);
			}
		return mapping;
	}

}
