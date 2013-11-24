package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.ReporteAulasDTO;
import mx.com.asteca.persistencia.entidades.ReporteAulas;

import org.springframework.stereotype.Component;

@Component(Constantes.ASSEMBLER_REPORTE_AULAS)
public class ReporteAulasAssembler extends Assembler<ReporteAulasDTO, ReporteAulas> {

	@Override
	public ReporteAulasDTO getDTOTransform(ReporteAulas mapping) {
		if(mapping == null){
			return null;
		}
		
		ReporteAulasDTO dto = new ReporteAulasDTO();
		if(mapping.getIdReporteAulas() >= 0){
			dto.setIdReporteAulas(mapping.getIdReporteAulas());
		}

		if(mapping.getInstructor() != null){
			dto.setInstructor(mapping.getInstructor());
		}

		if(mapping.getMes() != null){
			dto.setMes(mapping.getMes());
		}
		
		if(dto.getHoras() != null){
			dto.setHoras(mapping.getHoras());
		}
		
		return dto;
	}

	@Override
	public ReporteAulas getMappingTransform(ReporteAulasDTO dto) {
		if(dto == null){
			return null;
		}
		ReporteAulas mapping = new ReporteAulas();
		if(dto.getIdReporteAulas() >= 0){
			mapping.setIdReporteAulas(dto.getIdReporteAulas());
		}
		
		if(dto.getInstructor() != null){
			mapping.setInstructor(dto.getInstructor());
		}
		
		if(dto.getMes() != null){
			mapping.setMes(dto.getMes());
		}
		if(dto.getHoras() != null){
			mapping.setHoras(dto.getHoras());
		}
		return mapping;
	}

}
