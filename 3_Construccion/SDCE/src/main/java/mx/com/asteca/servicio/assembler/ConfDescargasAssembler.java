/**
 * 
 */
package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.ConfDescargasDTO;
import mx.com.asteca.persistencia.entidades.ConfDescargas;

import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 * 
 */
@Component(Constantes.ASSEMBLER_CONF_DESCARGAS)
public class ConfDescargasAssembler extends
		Assembler<ConfDescargasDTO, ConfDescargas> {

	@Override
	public ConfDescargasDTO getDTOTransform(ConfDescargas mapping) {
		if (mapping == null) {
			return null;
		}
		ConfDescargasDTO dto = new ConfDescargasDTO();
		dto.setRuta(mapping.getRuta());
		return dto;
	}

	@Override
	public ConfDescargas getMappingTransform(ConfDescargasDTO dto) {
		if (dto == null) {
			return null;
		}
		ConfDescargas mapping = new ConfDescargas();
		mapping.setRuta(dto.getRuta());
		return mapping;
	}

}
