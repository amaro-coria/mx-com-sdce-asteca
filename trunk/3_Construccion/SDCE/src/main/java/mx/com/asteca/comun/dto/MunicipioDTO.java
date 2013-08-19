package mx.com.asteca.comun.dto;

import java.io.Serializable;

public class MunicipioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idMunicipio;
	private int idEstado;
	private String nombre;
	private Short activo;
	private String clave;

	public int getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

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
		result = prime * result + idMunicipio;
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
		MunicipioDTO other = (MunicipioDTO) obj;
		if (idEstado != other.idEstado)
			return false;
		if (idMunicipio != other.idMunicipio)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MunicipioDTO [idMunicipio=" + idMunicipio + ", idEstado="
				+ idEstado + ", nombre=" + nombre + ", activo=" + activo
				+ ", clave=" + clave + "]";
	}

}
