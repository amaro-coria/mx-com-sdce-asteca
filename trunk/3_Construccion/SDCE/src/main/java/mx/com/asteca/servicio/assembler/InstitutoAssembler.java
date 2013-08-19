package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.InstitutosDTO;
import mx.com.asteca.persistencia.entidades.Institutos;

import org.springframework.stereotype.Component;

@Component(Constantes.ASSEMBLER_INSTITUTO)
public class InstitutoAssembler extends Assembler<InstitutosDTO, Institutos> {

	@Override
	public InstitutosDTO getDTOTransform(Institutos mapping) {
		if (mapping == null || mapping.getIdInstituto() == 0) {
			return null;
		}
		InstitutosDTO dto = new InstitutosDTO();
		dto.setIdInstituto(mapping.getIdInstituto());
		dto.setFax(mapping.getFax());
		dto.setNombre(mapping.getNombre());
		dto.setRazonSocial(mapping.getRazonSocial());
		dto.setRfc(mapping.getRfc());
		return dto;
	}

	@Override
	public Institutos getMappingTransform(InstitutosDTO dto) {
		if (dto == null) {
			return null;
		}
		Institutos mapping = new Institutos();
		mapping.setFax(dto.getFax());
		mapping.setIdDomicilio(dto.getIdDomicilio());
		mapping.setIdInstituto(dto.getIdInstituto());
		// mapping.setInstitutoContactoses(dto.getInstitutoContactoses());
		mapping.setNombre(dto.getNombre());
		mapping.setRazonSocial(dto.getRazonSocial());
		mapping.setRfc(dto.getRfc());

		return mapping;
	}
}
