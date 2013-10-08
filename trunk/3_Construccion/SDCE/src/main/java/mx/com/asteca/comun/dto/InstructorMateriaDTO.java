package mx.com.asteca.comun.dto;

import java.io.Serializable;

public class InstructorMateriaDTO implements Serializable{

	private int id;
	private int idInstructor;
	private MateriaRegistroDTO materia;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the idInstructor
	 */
	public int getIdInstructor() {
		return idInstructor;
	}
	/**
	 * @param idInstructor the idInstructor to set
	 */
	public void setIdInstructor(int idInstructor) {
		this.idInstructor = idInstructor;
	}
	/**
	 * @return the materia
	 */
	public MateriaRegistroDTO getMateria() {
		return materia;
	}
	/**
	 * @param materia the materia to set
	 */
	public void setMateria(MateriaRegistroDTO materia) {
		this.materia = materia;
	}
	
}
