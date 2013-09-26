package mx.com.asteca.comun.dto;

import java.io.Serializable;
import java.util.List;

public class ProgramaEstudiosMateriasDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idProgrEstMateria;
	private int idProgrEstudios;
	private MateriaRegistroDTO materia;
	private int horasTeoria;
	private int horasPractica;
	
	
	/**
	 * @return the idProgrEstMateria
	 */
	public int getIdProgrEstMateria() {
		return idProgrEstMateria;
	}
	/**
	 * @param idProgrEstMateria the idProgrEstMateria to set
	 */
	public void setIdProgrEstMateria(int idProgrEstMateria) {
		this.idProgrEstMateria = idProgrEstMateria;
	}
	/**
	 * @return the idProgrEstudios
	 */
	public int getIdProgrEstudios() {
		return idProgrEstudios;
	}
	/**
	 * @param idProgrEstudios the idProgrEstudios to set
	 */
	public void setIdProgrEstudios(int idProgrEstudios) {
		this.idProgrEstudios = idProgrEstudios;
	}
	/**
	 * @return the horasTeoria
	 */
	public int getHorasTeoria() {
		return horasTeoria;
	}
	/**
	 * @param horasTeoria the horasTeoria to set
	 */
	public void setHorasTeoria(int horasTeoria) {
		this.horasTeoria = horasTeoria;
	}
	/**
	 * @return the horasPractica
	 */
	public int getHorasPractica() {
		return horasPractica;
	}
	/**
	 * @param horasPractica the horasPractica to set
	 */
	public void setHorasPractica(int horasPractica) {
		this.horasPractica = horasPractica;
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
