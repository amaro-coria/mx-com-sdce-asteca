/**
 * 
 */
package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.TipoLicenciaDTO;
import mx.com.asteca.persistencia.entidades.TiposLicencia;

import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 * 
 */
@Component(Constantes.ASSEMBLER_TIPO_LICENCIA)
public class TipoLicenciaAssembler extends
		Assembler<TipoLicenciaDTO, TiposLicencia> {

	/* (non-Javadoc)
	 * @see mx.com.asteca.servicio.assembler.Assembler#getDTOTransform(java.lang.Object)
	 */
	@Override
	public TipoLicenciaDTO getDTOTransform(TiposLicencia mapping) {
		if (mapping == null) {
			return null;
		}
		TipoLicenciaDTO dto = new TipoLicenciaDTO();
		dto.setDescTipoLic(mapping.getDescTipoLic());
		dto.setIdTipoLic(mapping.getIdTipoLic()); // llave PK
		return dto;
	}

	/* (non-Javadoc)
	 * @see mx.com.asteca.servicio.assembler.Assembler#getMappingTransform(java.lang.Object)
	 */
	@Override
	public TiposLicencia getMappingTransform(TipoLicenciaDTO dto) {
		if (dto == null) {
			return null;
		}
		TiposLicencia mapping = new TiposLicencia();
		mapping.setDescTipoLic(dto.getDescTipoLic());
		if (dto.getIdTipoLic() != 0) {
			mapping.setIdTipoLic(dto.getIdTipoLic());
		}
		return mapping;
	}

}
