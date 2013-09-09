package mx.com.asteca.comun.dto;

import java.io.Serializable;

public class PaisDTO implements Serializable{

	 private int idPais;
     private String nombrePais;
     private Short activo;
	/**
	 * @return the idPais
	 */
	public int getIdPais() {
		return idPais;
	}
	/**
	 * @param idPais the idPais to set
	 */
	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}
	/**
	 * @return the nombrePais
	 */
	public String getNombrePais() {
		return nombrePais;
	}
	/**
	 * @param nombrePais the nombrePais to set
	 */
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}
	/**
	 * @return the activo
	 */
	public Short getActivo() {
		return activo;
	}
	/**
	 * @param activo the activo to set
	 */
	public void setActivo(Short activo) {
		this.activo = activo;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPais;
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
		PaisDTO other = (PaisDTO) obj;
		if (idPais != other.idPais)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaisDTO [idPais=" + idPais + ", nombrePais=" + nombrePais
				+ ", activo=" + activo + "]";
	}
	
	
     
}
