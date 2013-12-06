/**
 * 
 */
package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.CursoDTO;
import mx.com.asteca.persistencia.entidades.Cursos;

import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 * 
 */
@Component(Constantes.ASSEMBLER_CURSO)
public class CursoAssembler extends Assembler<CursoDTO, Cursos> {

	
	@Override
	public CursoDTO getDTOTransform(Cursos mapping) {
		if (mapping == null) {
			return null;
		}
		CursoDTO dto = new CursoDTO();
		
		dto.setFechaFin(mapping.getFechaFin());
		dto.setFechaIni(mapping.getFechaIni());
		dto.setGrupo(mapping.getGrupo());
		dto.setHoraFin(mapping.getHoraFin());
		dto.setHoraIni(mapping.getHoraIni());
		dto.setIdArea(mapping.getIdArea());
		dto.setIdCurso(mapping.getIdCurso());
		dto.setIdProgrEstudios(mapping.getIdProgrEstudios());
		dto.setIdSede(mapping.getIdSede());
		dto.setReferencia(mapping.getReferencia());
		dto.setNombre(mapping.getNombre());
		dto.setEmpresa(mapping.getEmpresa());
		dto.setAlumnos(mapping.getAlumnos());
		dto.setInstructor(mapping.getInstructor());
		dto.setHorasInstructor(mapping.getHorasInstructor());
		dto.setPractica(mapping.getPractica());
		dto.setDiasImp(mapping.getDiasImp());
		dto.setEmision(mapping.getEmision());
		return dto;

	}

	@Override
	public Cursos getMappingTransform(CursoDTO dto) {
		if(dto == null){
			return null;
		}
		Cursos mapping = new Cursos();
			
		mapping.setFechaFin(dto.getFechaFin());
		mapping.setFechaIni(dto.getFechaIni());
		mapping.setGrupo(dto.getGrupo());
		mapping.setHoraFin(dto.getHoraFin());
		mapping.setHoraIni(dto.getHoraIni());
		mapping.setIdArea(dto.getIdArea());
		mapping.setIdCurso(dto.getIdCurso());
		mapping.setIdProgrEstudios(dto.getIdProgrEstudios());
		mapping.setIdSede(dto.getIdSede());
		mapping.setReferencia(dto.getReferencia());
		mapping.setNombre(dto.getNombre());
		mapping.setEmpresa(dto.getEmpresa());
		mapping.setAlumnos(dto.getAlumnos());
		mapping.setInstructor(dto.getInstructor());
		mapping.setHorasInstructor(dto.getHorasInstructor());
		mapping.setPractica(dto.getPractica());
		mapping.setDiasImp(dto.getDiasImp());
		mapping.setEmision(dto.getEmision());
		return mapping;
	}

}
