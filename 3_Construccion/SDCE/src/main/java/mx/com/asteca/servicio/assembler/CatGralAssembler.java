package mx.com.asteca.servicio.assembler;

import org.springframework.stereotype.Component;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.CatGralDTO;
import mx.com.asteca.persistencia.entidades.CatGral;
import mx.com.asteca.persistencia.entidades.TiposCatGral;

@Component(Constantes.ASSEMBLER_CATGRAL)
public class CatGralAssembler extends Assembler<CatGralDTO, CatGral> {

	@Override
	public CatGralDTO getDTOTransform(CatGral mapping) {
		if (mapping == null || mapping.getIdCatGral() == 0) {
			return null;
		}
		CatGralDTO dto = new CatGralDTO();
		dto.setIdCatGral(mapping.getIdCatGral());
		dto.setCveRegistro(mapping.getCveRegistro());
		dto.setDsc(mapping.getDsc());
		dto.setEstatus(mapping.getEstatus());
		TiposCatGral tipoCatGral = mapping.getTiposCatGral();
		dto.setIdTipoCatGral(tipoCatGral.getIdCatGral());
		dto.setActivo(mapping.getActivo());
		dto.setTipo(mapping.getTiposCatGral().getTipo());
		return dto;
	}

	@Override
	public CatGral getMappingTransform(CatGralDTO dto) {
		if(dto == null){
			return null;
		}
		CatGral mapping = new CatGral();
		mapping.setActivo(dto.getActivo());
		mapping.setCveRegistro(dto.getCveRegistro());
		mapping.setDsc(dto.getDsc());
		mapping.setEstatus(dto.getEstatus());
		if(dto.getIdCatGral() != 0){
			mapping.setIdCatGral(dto.getIdCatGral());
		}
		if(dto.getIdTipoCatGral() != 0){
			TiposCatGral tipoCatGral = new TiposCatGral();
			tipoCatGral.setIdCatGral((short) dto.getIdTipoCatGral());
			mapping.setTiposCatGral(tipoCatGral);
		}
		return mapping;
	}

}
