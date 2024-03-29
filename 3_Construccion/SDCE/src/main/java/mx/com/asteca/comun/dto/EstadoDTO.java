package mx.com.asteca.comun.dto;

import java.io.Serializable;

public class EstadoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idEstado;
    private String nombre;
    private Short activo;
    private String clave;
    private int idPais;
    private String pais;
	public int getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Short getActivo() {
		return activo;
	}
	public void setActivo(Short activo) {
		this.activo = activo;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idEstado;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstadoDTO other = (EstadoDTO) obj;
		if (idEstado != other.idEstado)
			return false;
		return true;
	}
	
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
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}
	/**
	 * @param pais the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EstadoDTO [idEstado=" + idEstado + ", nombre=" + nombre
				+ ", activo=" + activo + ", clave=" + clave + ", idPais="
				+ idPais + ", pais=" + pais + "]";
	}
    
	
}
