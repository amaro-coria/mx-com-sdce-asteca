/**
 * 
 */
package mx.com.asteca.comun.dto;

import java.io.Serializable;

/**
 * @author JAMARO
 *
 */
public class TipoInstructorDTO implements Serializable {

	private short idTipoInstructor;
    private String nombre;
	/**
	 * @return the idTipoInstructor
	 */
	public short getIdTipoInstructor() {
		return idTipoInstructor;
	}
	/**
	 * @param idTipoInstructor the idTipoInstructor to set
	 */
	public void setIdTipoInstructor(short idTipoInstructor) {
		this.idTipoInstructor = idTipoInstructor;
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
