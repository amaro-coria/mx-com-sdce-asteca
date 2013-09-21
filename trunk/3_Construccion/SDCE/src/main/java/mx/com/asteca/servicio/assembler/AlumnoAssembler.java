/**
 * 
 */
package mx.com.asteca.servicio.assembler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.AlumnoDTO;
import mx.com.asteca.comun.dto.CapacidadDTO;
import mx.com.asteca.comun.dto.DocumentoDTO;
import mx.com.asteca.comun.dto.DomicilioDTO;
import mx.com.asteca.comun.dto.EstudioDTO;
import mx.com.asteca.comun.dto.FamiliaDTO;
import mx.com.asteca.comun.dto.PersonaDTO;
import mx.com.asteca.comun.dto.ReferenciaDTO;
import mx.com.asteca.persistencia.entidades.Alumnos;
import mx.com.asteca.persistencia.entidades.Capacidades;
import mx.com.asteca.persistencia.entidades.CapacidadesAlumnos;
import mx.com.asteca.persistencia.entidades.Docs;
import mx.com.asteca.persistencia.entidades.DocsAlumnos;
import mx.com.asteca.persistencia.entidades.Domicilios;
import mx.com.asteca.persistencia.entidades.Estatus;
import mx.com.asteca.persistencia.entidades.Estudios;
import mx.com.asteca.persistencia.entidades.EstudiosAlumno;
import mx.com.asteca.persistencia.entidades.Familia;
import mx.com.asteca.persistencia.entidades.Personas;
import mx.com.asteca.persistencia.entidades.Referencias;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 * 
 */
@Component(Constantes.ASSEMBLER_ALUMNO)
public class AlumnoAssembler extends Assembler<AlumnoDTO, Alumnos> {

	@Autowired
	@Qualifier(Constantes.ASSEMBLER_PERSONA)
	private Assembler assemblerPersona;
	
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_DOMICILIO)
	private Assembler assemblerDomicilio;
	
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_CAPACIDADES)
	private Assembler assemblerCapacidades;
	
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_ESTUDIO)
	private Assembler assemblerEstudios;
	
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_FAMILIA)
	private Assembler assemblerFamilia;
	
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_DOCS)
	private Assembler assemblerDocs;
	
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_REFERENCIAS)
	private Assembler assemblerReferencia;
	
	@Override
	public AlumnoDTO getDTOTransform(Alumnos mapping) {
		if (mapping == null) {
			return null;
		}
		AlumnoDTO dto = new AlumnoDTO();
		if(mapping.getEstatus() != null){
			dto.setEstatus(mapping.getEstatus().getDescEstatus());
			dto.setIdEstatus(mapping.getEstatus().getIdEstatus());
		}
		dto.setIdAlumno(mapping.getIdAlumno()); // llave PK
		dto.setMatricula(mapping.getMatricula());
		dto.setNombre(mapping.getPersonas().getNombre() + " "
				+ mapping.getPersonas().getApellidoP() + " "
				+ mapping.getPersonas().getApellidoM());
		if(mapping.getPersonas() != null){
			Personas mappingPersonas = mapping.getPersonas();
			PersonaDTO dtoPersona = (PersonaDTO) assemblerPersona.getDTOTransform(mappingPersonas);
			dto.setDtoPersona(dtoPersona);
		}
		if(mapping.getDomicilios() != null){
			Domicilios mappingDomicilio = mapping.getDomicilios();
			DomicilioDTO dtoDomicilio = (DomicilioDTO) assemblerDomicilio.getDTOTransform(mappingDomicilio);
			dto.setDtoDomicilio(dtoDomicilio);
		}
		if(!CollectionUtils.isEmpty(mapping.getCapacidadesAlumnoses())){
			Set<CapacidadesAlumnos> setCapacidadesMapping = mapping.getCapacidadesAlumnoses();
			List<CapacidadDTO> listCapacidades = new ArrayList<CapacidadDTO>();
			for(CapacidadesAlumnos capAlumno : setCapacidadesMapping){
				Capacidades capMapping =  capAlumno.getCapacidades();
				CapacidadDTO dtoCapacidad = (CapacidadDTO) assemblerCapacidades.getDTOTransform(capMapping); 
				listCapacidades.add(dtoCapacidad);
			}
			dto.setListaCapacidades(listCapacidades);
		}
		if(!CollectionUtils.isEmpty(mapping.getEstudiosAlumnos())){
			Set<EstudiosAlumno> setEstudios = mapping.getEstudiosAlumnos();
			List<EstudioDTO> dtoEstudiosList = new ArrayList<EstudioDTO>();
			for(EstudiosAlumno estAlumno : setEstudios){
				Estudios mappingEstudios = estAlumno.getEstudios();
				EstudioDTO dtoEstudio = (EstudioDTO) assemblerEstudios.getDTOTransform(mappingEstudios);
				dtoEstudiosList.add(dtoEstudio);
			}
			dto.setListaEstudios(dtoEstudiosList);
		}
		if(mapping.getFamilia() != null){
			Familia mappingFamilia = mapping.getFamilia();
			FamiliaDTO dtoFamilia = (FamiliaDTO) assemblerFamilia.getDTOTransform(mappingFamilia);
			dto.setDtoFamilia(dtoFamilia);
		}
		if(!CollectionUtils.isEmpty(mapping.getReferenciases())){
			Set<Referencias> setReferencias = mapping.getReferenciases();
			List<ReferenciaDTO> listaReferencias = new ArrayList<ReferenciaDTO>();
			for(Referencias ref : setReferencias){
				ReferenciaDTO refDTO = (ReferenciaDTO) assemblerReferencia.getDTOTransform(ref);
				listaReferencias.add(refDTO);
			}
			dto.setListaReferencias(listaReferencias);
		}
		if(!CollectionUtils.isEmpty(mapping.getDocsAlumnoses())){
			Set<DocsAlumnos> setDocsAlumnos = mapping.getDocsAlumnoses();
			List<DocumentoDTO> listaDocs = new ArrayList<DocumentoDTO>();
			for(DocsAlumnos doc : setDocsAlumnos){
				Docs mappingdoc = doc.getDocs();
				DocumentoDTO dtoDoc = (DocumentoDTO) assemblerDocs.getDTOTransform(mappingdoc);
				listaDocs.add(dtoDoc);
			}
			dto.setListaDocumentos(listaDocs);
		}
		return dto;

	}

	@Override
	public Alumnos getMappingTransform(AlumnoDTO dto) {
		if(dto == null){
			return null;
		}
		Alumnos mapping = new Alumnos();
			mapping.setMatricula(dto.getMatricula());
			if(dto.getDtoPersona() != null){
				PersonaDTO dtoPersona = dto.getDtoPersona();
				Personas personas = (Personas) assemblerPersona.getMappingTransform(dtoPersona);
				mapping.setPersonas(personas);
			}
			if(dto.getDtoDomicilio() != null){
				DomicilioDTO dtoDomicilio = dto.getDtoDomicilio();
				Domicilios domicilios = (Domicilios) assemblerDomicilio.getMappingTransform(dtoDomicilio);
				mapping.setDomicilios(domicilios);
			}
			if(dto.getDtoFamilia() != null){
				FamiliaDTO dtoFamilia = dto.getDtoFamilia();
				Familia familia = (Familia) assemblerFamilia.getMappingTransform(dtoFamilia);
				mapping.setFamilia(familia);
			}
			if(dto.getIdEstatus() != 0){
				Estatus estatus = new Estatus();
				estatus.setIdEstatus((short) dto.getIdEstatus());
				mapping.setEstatus(estatus);
			}
			return mapping;
	}

}
