/**
 * 
 */
package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.InstructorMateriaDTO;
import mx.com.asteca.comun.dto.MateriaRegistroDTO;
import mx.com.asteca.persistencia.entidades.Instructores;
import mx.com.asteca.persistencia.entidades.InstructoresMaterias;
import mx.com.asteca.persistencia.entidades.MateriasRegistros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 * 
 */
@Component(Constantes.ASSEMBLER_INSTRUCTORES_MATERIAS)
public class InstructorMateriaAssembler extends
		Assembler<InstructorMateriaDTO, InstructoresMaterias> {

	@Autowired
	@Qualifier(Constantes.ASSEMBLER_MATERIAS_REGISTROS)
	private Assembler assemblerMateriaRegistro;
	
	@Override
	public InstructorMateriaDTO getDTOTransform(InstructoresMaterias mapping) {
		if (mapping == null) {
			return null;
		}
		InstructorMateriaDTO dto = new InstructorMateriaDTO();
		dto.setId(mapping.getIdInstructorMateria());
		dto.setIdInstructor(mapping.getInstructores().getIdInstructor());
		dto.setMateria((MateriaRegistroDTO) assemblerMateriaRegistro.getDTOTransform(mapping.getMaterias()));
		return dto;
	}

	@Override
	public InstructoresMaterias getMappingTransform(InstructorMateriaDTO dto) {
		if(dto == null){
			return null;
		}
		InstructoresMaterias mapping = new InstructoresMaterias();
		if(dto.getId() != 0){
			mapping.setIdInstructorMateria(dto.getId());
		}
		if(dto.getIdInstructor() != 0){
			Instructores instructores = new Instructores();
			instructores.setIdInstructor(dto.getIdInstructor());
			mapping.setInstructores(instructores);
		}
		mapping.setMaterias((MateriasRegistros) assemblerMateriaRegistro.getMappingTransform(dto.getMateria()));
		return mapping;
	}

}
