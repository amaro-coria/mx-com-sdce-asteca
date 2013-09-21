/**
 * 
 */
package mx.com.asteca.comun.dto;

import java.io.Serializable;

/**
 * @author JAMARO
 *
 */
public class TipoEstudioDTO implements Serializable {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private short idTipoEstudios;
     private String nombre;
	/**
	 * @return the idTipoEstudios
	 */
	public short getIdTipoEstudios() {
		return idTipoEstudios;
	}
	/**
	 * @param idTipoEstudios the idTipoEstudios to set
	 */
	public void setIdTipoEstudios(short idTipoEstudios) {
		this.idTipoEstudios = idTipoEstudios;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTipoEstudios;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoEstudioDTO other = (TipoEstudioDTO) obj;
		if (idTipoEstudios != other.idTipoEstudios)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TipoEstudioDTO [idTipoEstudios=" + idTipoEstudios + ", nombre="
				+ nombre + "]";
	}
     
	
}
