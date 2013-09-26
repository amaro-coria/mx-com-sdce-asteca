/**
 * 
 */
package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.MateriaDTO;
import mx.com.asteca.persistencia.entidades.Aulas;
import mx.com.asteca.persistencia.entidades.CatGral;
import mx.com.asteca.persistencia.entidades.Instructores;
import mx.com.asteca.persistencia.entidades.Materias;
import mx.com.asteca.persistencia.entidades.MateriasRegistros;

import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 *
 */
@Component(Constantes.ASSEMBLER_MATERIAS)
public class MateriaAssembler extends Assembler<MateriaDTO, Materias> {

	@Override
	public MateriaDTO getDTOTransform(Materias mapping) {
		if(mapping == null){
			return null;
		}
		MateriaDTO dto = new MateriaDTO();
			dto.setFechaFinal(mapping.getFhFinal());
			dto.setFechaInicial(mapping.getFhInicial());
			dto.setIdAula(mapping.getAulas().getIdAula());
			dto.setIdInstructor(mapping.getInstructores().getIdInstructor());
			dto.setIdMateria(mapping.getIdMateria());
			dto.setIdTipo(mapping.getCatGral().getIdCatGral());
			dto.setNombre(mapping.getMateriasRegistros().getNombre());
			dto.setIdMateriaRegistro(mapping.getMateriasRegistros().getIdMateria());
			dto.setNombreAula(mapping.getAulas().getDsc());
			String nombre = mapping.getInstructores().getPersonas().getNombre() + " " + mapping.getInstructores().getPersonas().getApellidoP() + " " + mapping.getInstructores().getPersonas().getApellidoM();
			dto.setNombreInstructor(nombre);
			dto.setTipoNombre(mapping.getCatGral().getDsc());
		return dto;
	}

	@Override
	public Materias getMappingTransform(MateriaDTO dto) {
		if(dto == null){
			return null;
		}
		Materias materias = new Materias();
			if(dto.getIdAula() != 0){
				Aulas aula = new Aulas();
				aula.setIdAula(dto.getIdAula());
				materias.setAulas(aula);
			}
			if(dto.getIdTipo() != 0){
				CatGral catGral = new CatGral();
				catGral.setIdCatGral(dto.getIdTipo());
				materias.setCatGral(catGral);
			}
			materias.setFhFinal(dto.getFechaFinal());
			materias.setFhInicial(dto.getFechaInicial());
			if(dto.getIdMateria() != 0){
				materias.setIdMateria(dto.getIdMateria());
			}
			if(dto.getIdInstructor() != 0){
				Instructores instructores = new Instructores();
				instructores.setIdInstructor(dto.getIdInstructor());
				materias.setInstructores(instructores);
			}
			if(dto.getIdMateriaRegistro() != 0){
				MateriasRegistros materiasRegistros = new MateriasRegistros();
				materiasRegistros.setIdMateria(dto.getIdMateriaRegistro());
				materias.setMateriasRegistros(materiasRegistros);
			}
		return materias;
	}

}
