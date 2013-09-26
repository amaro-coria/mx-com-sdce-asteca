package mx.com.asteca.servicio.assembler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.DomicilioDTO;
import mx.com.asteca.comun.dto.InstitutoContactoDTO;
import mx.com.asteca.comun.dto.InstitutoDTO;
import mx.com.asteca.comun.dto.PersonaDTO;
import mx.com.asteca.persistencia.entidades.Domicilios;
import mx.com.asteca.persistencia.entidades.InstitutoContactos;
import mx.com.asteca.persistencia.entidades.Institutos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component(Constantes.ASSEMBLER_INSTITUTO)
public class InstitutoAssembler extends Assembler<InstitutoDTO, Institutos> {

	@Autowired
	@Qualifier(Constantes.ASSEMBLER_DOMICILIO)
	private Assembler assemblerDomicilio;
	
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_PERSONA)
	private Assembler assemblerPersona;
	
	@Override
	public InstitutoDTO getDTOTransform(Institutos mapping) {
		if (mapping == null || mapping.getIdInstituto() == 0) {
			return null;
		}
		InstitutoDTO dto = new InstitutoDTO();
		dto.setIdInstituto(mapping.getIdInstituto());
		dto.setFax(mapping.getFax());
		dto.setNombre(mapping.getNombre());
		dto.setRazonSocial(mapping.getRazonSocial());
		dto.setRfc(mapping.getRfc());
		dto.setDtoDomicilio((DomicilioDTO) assemblerDomicilio.getDTOTransform(mapping.getDomicilios()));
		Set<InstitutoContactos> set =  mapping.getInstitutoContactoses();
		List<InstitutoContactoDTO> listContactos = new ArrayList<InstitutoContactoDTO>();
		if(!CollectionUtils.isEmpty(set)){
			for(InstitutoContactos contacto : set){
				InstitutoContactoDTO con = new InstitutoContactoDTO();
				con.setDtoPersona((PersonaDTO) assemblerPersona.getDTOTransform(contacto.getPersonas()));
				con.setIdInstitutoContacto(contacto.getIdInstCont());
				con.setIdPuesto(contacto.getInstitutoPuestos().getIdPuesto());
				con.setPuesto(contacto.getInstitutoPuestos().getNombre());
				listContactos.add(con);
			}
		}
		dto.setListContactos(listContactos);
		return dto;
	}

	@Override
	public Institutos getMappingTransform(InstitutoDTO dto) {
		if (dto == null) {
			return null;
		}
		Institutos mapping = new Institutos();
		mapping.setFax(dto.getFax());
		mapping.setDomicilios((Domicilios) assemblerDomicilio.getMappingTransform(dto.getDtoDomicilio()));
		mapping.setIdInstituto(dto.getIdInstituto());
		// mapping.setInstitutoContactoses(dto.getInstitutoContactoses());
		mapping.setNombre(dto.getNombre());
		mapping.setRazonSocial(dto.getRazonSocial());
		mapping.setRfc(dto.getRfc());
		return mapping;
	}
}
