package mx.com.asteca.comun.dto;

import java.io.Serializable;


/**
 * @author Jorge Amaro Coria
 * @version 1.0
 * @since 1.0
 *
 */
public class AulaDTO implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idAula;
     private String sede;
     private int idSede;
     private String clave;
     private String dsc;
     private int capacidad;
     private Short activo;
     
     
	/**
	 * @return the idAula
	 */
	public int getIdAula() {
		return idAula;
	}
	/**
	 * @param idAula the idAula to set
	 */
	public void setIdAula(int idAula) {
		this.idAula = idAula;
	}
	/**
	 * @return the sede
	 */
	public String getSede() {
		return sede;
	}
	/**
	 * @param sede the sede to set
	 */
	public void setSede(String sede) {
		this.sede = sede;
	}
	/**
	 * @return the idSede
	 */
	public int getIdSede() {
		return idSede;
	}
	/**
	 * @param idSede the idSede to set
	 */
	public void setIdSede(int idSede) {
		this.idSede = idSede;
	}
	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}
	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}
	/**
	 * @return the dsc
	 */
	public String getDsc() {
		return dsc;
	}
	/**
	 * @param dsc the dsc to set
	 */
	public void setDsc(String dsc) {
		this.dsc = dsc;
	}
	/**
	 * @return the capacidad
	 */
	public int getCapacidad() {
		return capacidad;
	}
	/**
	 * @param capacidad the capacidad to set
	 */
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
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
		result = prime * result + idAula;
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
		AulaDTO other = (AulaDTO) obj;
		if (idAula != other.idAula)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AulaDTO [idAula=" + idAula + ", sede=" + sede + ", idSede="
				+ idSede + ", clave=" + clave + ", dsc=" + dsc + ", capacidad="
				+ capacidad + ", activo=" + activo + "]";
	}
	
}
