/**
 * 
 */
package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.EstatusDTO;
import mx.com.asteca.persistencia.entidades.Estatus;

import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 *
 */
@Component(Constantes.ASSEMBLER_ESTATUS)
public class EstatusAssembler extends Assembler<EstatusDTO, Estatus> {

	@Override
	public EstatusDTO getDTOTransform(Estatus mapping) {
		if(mapping == null){
			return null;
		}
		EstatusDTO dto = new EstatusDTO();
			dto.setDescEstatus(mapping.getDescEstatus());
			dto.setIdEstatus(mapping.getIdEstatus());
			dto.setObsEstatus(mapping.getObsEstatus());
		return dto;
	}

	@Override
	public Estatus getMappingTransform(EstatusDTO dto) {
		if(dto == null){
			return null;
		}
		Estatus mapping = new Estatus();
			mapping.setDescEstatus(dto.getDescEstatus());
			mapping.setObsEstatus(dto.getObsEstatus());
			if(dto.getIdEstatus() != 0){
				mapping.setIdEstatus(dto.getIdEstatus());
			}
		return mapping;
	}

}
