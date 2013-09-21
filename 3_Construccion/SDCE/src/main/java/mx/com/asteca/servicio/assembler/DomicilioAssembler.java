/**
 * 
 */
package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.DomicilioDTO;
import mx.com.asteca.persistencia.entidades.Asentamientos;
import mx.com.asteca.persistencia.entidades.AsentamientosId;
import mx.com.asteca.persistencia.entidades.Domicilios;

import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 * 
 */
@Component(Constantes.ASSEMBLER_DOMICILIO)
public class DomicilioAssembler extends Assembler<DomicilioDTO, Domicilios> {

	@Override
	public DomicilioDTO getDTOTransform(Domicilios mapping) {
		if (mapping == null) {
			return null;
		}
		DomicilioDTO dto = new DomicilioDTO();
		dto.setCalle(mapping.getCalle());
		dto.setCp(mapping.getCp());
		dto.setNoExterior(mapping.getNoExterior());
		dto.setNoInterior(mapping.getNoInterior());
		dto.setTelefono(mapping.getTelefono());
		if(mapping.getAsentamientos() != null){
			dto.setIdAsentamiento(mapping.getAsentamientos().getId().getIdAsentamiento());
			dto.setIdMunicipio(mapping.getAsentamientos().getId().getIdMunicipio());
			dto.setIdEstado(mapping.getAsentamientos().getId().getIdEstado());
			dto.setEstado(mapping.getAsentamientos().getMunicipios().getEstados().getNombre());
			dto.setMunicipio(mapping.getAsentamientos().getMunicipios().getNombre());
			dto.setAsentamiento(mapping.getAsentamientos().getNombre());
		}
		dto.setIdDomicilio(mapping.getIdDomicilio());
		
		return dto;
	}

	@Override
	public Domicilios getMappingTransform(DomicilioDTO dto) {
		if (dto == null) {
			return null;
		}
		Domicilios mapping = new Domicilios();
		mapping.setCalle(dto.getCalle());
		mapping.setCp(dto.getCp());
		mapping.setNoExterior(dto.getNoExterior());
		mapping.setNoInterior(dto.getNoInterior());
		mapping.setTelefono(dto.getTelefono());
		if (dto.getIdAsentamiento() != 0) {
			mapping.setIdDomicilio(dto.getIdDomicilio());
		}
		if(dto.getIdAsentamiento() != 0 && dto.getIdMunicipio() !=0 && dto.getIdEstado() != 0){
			Asentamientos asentamiento = new Asentamientos();
			AsentamientosId id = new AsentamientosId();
			id.setIdAsentamiento(dto.getIdAsentamiento());
			id.setIdEstado(dto.getIdEstado());
			id.setIdMunicipio(dto.getIdMunicipio());
			asentamiento.setId(id);
			mapping.setAsentamientos(asentamiento);
		}
		return mapping;
	}

}
