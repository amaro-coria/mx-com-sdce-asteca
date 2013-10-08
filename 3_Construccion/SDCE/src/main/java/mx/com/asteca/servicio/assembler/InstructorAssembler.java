/**
 * 
 */
package mx.com.asteca.servicio.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.DomicilioDTO;
import mx.com.asteca.comun.dto.InstructorDTO;
import mx.com.asteca.comun.dto.InstructorDocumentoDTO;
import mx.com.asteca.comun.dto.InstructorMateriaDTO;
import mx.com.asteca.comun.dto.PersonaDTO;
import mx.com.asteca.persistencia.entidades.Domicilios;
import mx.com.asteca.persistencia.entidades.Estatus;
import mx.com.asteca.persistencia.entidades.Instructores;
import mx.com.asteca.persistencia.entidades.InstructoresDocumentos;
import mx.com.asteca.persistencia.entidades.InstructoresMaterias;
import mx.com.asteca.persistencia.entidades.Personas;
import mx.com.asteca.persistencia.entidades.TiposInstructores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @author JAMARO
 * 
 */
@Component(Constantes.ASSEMBLER_INSTRUCTORES)
public class InstructorAssembler extends Assembler<InstructorDTO, Instructores> {

	@Autowired
	@Qualifier(Constantes.ASSEMBLER_INSTRUCTORES_MATERIAS)
	private Assembler assemblerInstructoresMaterias;

	@Autowired
	@Qualifier(Constantes.ASSEMBLER_INSTRUCTORES_DOCUMENTOS)
	private Assembler assemblerInstructoresDocumentos;

	@Autowired
	@Qualifier(Constantes.ASSEMBLER_PERSONA)
	private Assembler assemblerPesona;

	@Autowired
	@Qualifier(Constantes.ASSEMBLER_DOMICILIO)
	private Assembler assemblerDomicilio;

	@Override
	public InstructorDTO getDTOTransform(Instructores mapping) {
		if (mapping == null) {
			return null;
		}
		InstructorDTO dto = new InstructorDTO();
		dto.setDtoDomicilio((DomicilioDTO) assemblerDomicilio
				.getDTOTransform(mapping.getDomicilios()));
		dto.setDtoPersona((PersonaDTO) assemblerPesona.getDTOTransform(mapping
				.getPersonas()));
		dto.setIdInstructor(mapping.getIdInstructor());
		dto.setIdTipo(mapping.getTiposInstructores().getIdTipoInstructor());
		dto.setTipo(mapping.getTiposInstructores().getNombre());
		dto.setNoEmpleado(mapping.getNoEmpleado());
		Set<InstructoresDocumentos> set = mapping.getInstructoresDocumentoses();
		List<InstructorDocumentoDTO> lista = new ArrayList<InstructorDocumentoDTO>();
		if (!CollectionUtils.isEmpty(set)) {
			for (InstructoresDocumentos map : set) {
				InstructorDocumentoDTO dto1 = (InstructorDocumentoDTO) assemblerInstructoresDocumentos
						.getDTOTransform(map);
				lista.add(dto1);
			}
		}
		dto.setListInstructoresDocumentos(lista);
		Set<InstructoresMaterias> set2 = mapping.getInstructoresMateriases();
		List<InstructorMateriaDTO> lista2 = new ArrayList<InstructorMateriaDTO>();
		if (!CollectionUtils.isEmpty(set2)) {
			for (InstructoresMaterias map : set2) {
				InstructorMateriaDTO dto2 = (InstructorMateriaDTO) assemblerInstructoresMaterias
						.getDTOTransform(map);
				lista2.add(dto2);
			}
		}
		dto.setListInstructoresMaterias(lista2);
		if(mapping.getEstatus() != null){
			dto.setIdEstatus(mapping.getEstatus().getIdEstatus());
			dto.setEstatus(mapping.getEstatus().getDescEstatus());
		}
		dto.setNombre(mapping.getPersonas().getNombre()+" "+mapping.getPersonas().getApellidoP()+" "+mapping.getPersonas().getApellidoM());
		return dto;
	}

	@Override
	public Instructores getMappingTransform(InstructorDTO dto) {
		if (dto == null) {
			return null;
		}
		Instructores mapping = new Instructores();
		mapping.setDomicilios((Domicilios) assemblerDomicilio
				.getMappingTransform(dto.getDtoDomicilio()));
		if (dto.getIdInstructor() != 0) {
			mapping.setIdInstructor(dto.getIdInstructor());
		}
		mapping.setNoEmpleado(dto.getNoEmpleado());
		mapping.setPersonas((Personas) assemblerPesona.getMappingTransform(dto
				.getDtoPersona()));
		if (dto.getIdTipo() != 0) {
			TiposInstructores tiposInstructores = new TiposInstructores();
			tiposInstructores.setIdTipoInstructor(dto.getIdTipo());
			mapping.setTiposInstructores(tiposInstructores);
		}
		if(dto.getIdEstatus() != 0){
			Estatus estatus = new Estatus();
			estatus.setIdEstatus(dto.getIdEstatus());
			mapping.setEstatus(estatus);
		}
		return mapping;
	}

}
