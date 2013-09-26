/**
 * 
 */
package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.MateriaRegistroDTO;
import mx.com.asteca.persistencia.entidades.MateriasRegistros;

import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 * 
 */
@Component(Constantes.ASSEMBLER_MATERIAS_REGISTROS)
public class MateriaRegistroAssembler extends
		Assembler<MateriaRegistroDTO, MateriasRegistros> {

	@Override
	public MateriaRegistroDTO getDTOTransform(MateriasRegistros mapping) {
		if (mapping == null) {
			return null;
		}
		MateriaRegistroDTO dto = new MateriaRegistroDTO();
		dto.setIdMateria(mapping.getIdMateria());
		dto.setNombre(mapping.getNombre());
		return dto;
	}

	@Override
	public MateriasRegistros getMappingTransform(MateriaRegistroDTO dto) {
		if (dto == null) {
			return null;
		}
		MateriasRegistros mapping = new MateriasRegistros();
		if (dto.getIdMateria() != 0) {
			mapping.setIdMateria(dto.getIdMateria());
		}
		mapping.setNombre(dto.getNombre());
		return mapping;
	}

}
