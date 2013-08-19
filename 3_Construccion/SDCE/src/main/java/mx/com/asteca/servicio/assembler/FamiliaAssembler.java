/**
 * 
 */
package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.FamiliaDTO;
import mx.com.asteca.persistencia.entidades.Familia;

import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 *
 */
@Component(Constantes.ASSEMBLER_FAMILIA)
public class FamiliaAssembler extends Assembler<FamiliaDTO, Familia> {

	@Override
	public FamiliaDTO getDTOTransform(Familia mapping) {
		if(mapping == null){
			return null;
		}
		FamiliaDTO dto = new FamiliaDTO();
			dto.setConyugueFechaNac(mapping.getConyugueFechaNac());
			dto.setConyugueNombre(mapping.getConyugueNombre());
			dto.setHijo1FechaNac(mapping.getHijo1FechaNac());
			dto.setHijo1Nombre(mapping.getHijo1Nombre());
			dto.setHijo2FechaNac(mapping.getHijo2FechaNac());
			dto.setHijo2Nombre(mapping.getHijo2Nombre());
			dto.setIdFam(mapping.getIdFam());
			dto.setMadreFechaNac(mapping.getMadreFechaNac());
			dto.setMadreNombre(mapping.getMadreNombre());
			dto.setPadreFechaNac(mapping.getPadreFechaNac());
			dto.setPadreNombre(mapping.getPadreNombre());
		return dto;
	}

	@Override
	public Familia getMappingTransform(FamiliaDTO dto) {
		if(dto == null){
			return null;
		}
		Familia mapping = new Familia();
			mapping.setConyugueFechaNac(dto.getConyugueFechaNac());
			mapping.setConyugueNombre(dto.getConyugueNombre());
			mapping.setHijo1FechaNac(dto.getHijo1FechaNac());
			mapping.setHijo1Nombre(dto.getHijo1Nombre());
			mapping.setHijo2FechaNac(dto.getHijo2FechaNac());
			mapping.setHijo2Nombre(dto.getHijo2Nombre());			
			mapping.setMadreFechaNac(dto.getMadreFechaNac());
			mapping.setMadreNombre(dto.getMadreNombre());
			mapping.setPadreFechaNac(dto.getPadreFechaNac());
			mapping.setPadreNombre(dto.getPadreNombre());
			if(dto.getIdFam() != 0){
				mapping.setIdFam(dto.getIdFam());
			}
		return mapping;
	}

}
