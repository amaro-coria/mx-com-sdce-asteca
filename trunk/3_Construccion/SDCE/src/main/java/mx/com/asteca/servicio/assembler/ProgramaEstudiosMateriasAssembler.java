/**
 * 
 */
package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.MateriaRegistroDTO;
import mx.com.asteca.comun.dto.ProgramaEstudiosMateriasDTO;
import mx.com.asteca.persistencia.entidades.MateriasRegistros;
import mx.com.asteca.persistencia.entidades.ProgramaEstMaterias;
import mx.com.asteca.persistencia.entidades.ProgramaEstudios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 * 
 */
@Component(Constantes.ASSEMBLER_PROGRAMA_ESTUDIOS_MATERIAS_REG)
public class ProgramaEstudiosMateriasAssembler extends
		Assembler<ProgramaEstudiosMateriasDTO, ProgramaEstMaterias> {

	@Autowired
	@Qualifier(Constantes.ASSEMBLER_MATERIAS_REGISTROS)
	private Assembler assemblerMaterias;

	@Override
	public ProgramaEstudiosMateriasDTO getDTOTransform(
			ProgramaEstMaterias mapping) {
		if (mapping == null) {
			return null;
		}
		ProgramaEstudiosMateriasDTO dto = new ProgramaEstudiosMateriasDTO();
		dto.setHorasPractica(mapping.getHorasPractica());
		dto.setHorasTeoria(mapping.getHorasTeoria());
		dto.setIdProgrEstMateria(mapping.getIdProgrEstMat());
		dto.setIdProgrEstudios(mapping.getProgramaEstudios().getIdProgEstudio());
		dto.setMateria((MateriaRegistroDTO) assemblerMaterias
				.getDTOTransform(mapping.getMaterias()));
		return dto;
	}

	@Override
	public ProgramaEstMaterias getMappingTransform(
			ProgramaEstudiosMateriasDTO dto) {
		if (dto == null) {
			return null;
		}
		ProgramaEstMaterias mapping = new ProgramaEstMaterias();
		mapping.setHorasPractica(dto.getHorasPractica());
		mapping.setHorasTeoria(dto.getHorasTeoria());
		if (dto.getIdProgrEstMateria() != 0) {
			mapping.setIdProgrEstMat(dto.getIdProgrEstMateria());
		}
		if (dto.getIdProgrEstudios() != 0) {
			ProgramaEstudios programaEstudios = new ProgramaEstudios();
			programaEstudios.setIdProgEstudio(dto.getIdProgrEstudios());
			mapping.setProgramaEstudios(programaEstudios);
		}
		mapping.setMaterias((MateriasRegistros) assemblerMaterias
				.getMappingTransform(dto.getMateria()));
		return mapping;
	}

}
