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
		
		return mapping;
	}

}
