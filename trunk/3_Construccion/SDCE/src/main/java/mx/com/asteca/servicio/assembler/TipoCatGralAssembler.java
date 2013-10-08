/**
 * 
 */
package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.TipoCatGralDTO;
import mx.com.asteca.persistencia.entidades.TiposCatGral;

import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 *
 */
@Component(Constantes.ASSEMBLER_TIPOS_CAT_GRAL)
public class TipoCatGralAssembler extends
		Assembler<TipoCatGralDTO, TiposCatGral> {

	@Override
	public TipoCatGralDTO getDTOTransform(TiposCatGral mapping) {
		if(mapping == null){
			return null;
		}
		TipoCatGralDTO dto = new TipoCatGralDTO();
		dto.setIdCatGral(mapping.getIdCatGral());
		dto.setTipo(mapping.getTipo());
		return dto;
	}

	@Override
	public TiposCatGral getMappingTransform(TipoCatGralDTO dto) {
		if(dto == null){
			return null;
		}
		TiposCatGral mapping = new TiposCatGral();
		mapping.setTipo(dto.getTipo());
		if(dto.getIdCatGral() != 0){
			mapping.setIdCatGral(dto.getIdCatGral());
		}
		return mapping;
	}

}
