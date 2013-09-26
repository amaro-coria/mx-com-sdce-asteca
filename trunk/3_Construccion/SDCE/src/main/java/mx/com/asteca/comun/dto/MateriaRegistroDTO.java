package mx.com.asteca.comun.dto;

import java.io.Serializable;

public class MateriaRegistroDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idMateria;
	private String nombre;
	/**
	 * @return the idMateria
	 */
	public int getIdMateria() {
		return idMateria;
	}
	/**
	 * @param idMateria the idMateria to set
	 */
	public void setIdMateria(int idMateria) {
		this.idMateria = idMateria;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
